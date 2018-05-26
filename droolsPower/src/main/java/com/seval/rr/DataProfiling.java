package com.seval.rr;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.drools.io.ResourceFactory;
import org.drools.io.impl.ByteArrayResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.mvel2.optimizers.OptimizerFactory;

import com.seval.rr.Trade;

public class DataProfiling {
    private static KnowledgeBase kbase;

    private static final String DATA_FILE = "drools/trades.csv";

    public static void main(String[] args) throws IOException, URISyntaxException {
        OptimizerFactory.setDefaultOptimizer("reflective");
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        loadKnowledgeBase("drools/trades-rules.xls", "drools/trades.drt", "Sheet1", 2, 1, kbuilder);
        kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        process();
    }

    private static void process() throws URISyntaxException, IOException  {
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.newStatefulKnowledgeSession();
        URL resource = ClassLoader.getSystemResource(DATA_FILE);
        Path inputFile = Paths.get(resource.toURI());
        Stream<String> lines = Files.lines(inputFile, StandardCharsets.UTF_8);

        int i = 0;

        for (String line : (Iterable<String>) lines::iterator) {
            String[] split = line.split(",");
            Trade trade = new Trade(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7]);

            if (i > 0) {
                ksession.insert(trade);
            }
            i++;
        }
        lines.close();
        ksession.fireAllRules();
    }

    private static void loadKnowledgeBase(String aXlsName, String aDrlName, String aWorksheetName, int aStartRow,
            int aStartCol, KnowledgeBuilder aKbuilder) throws IOException {
        ExternalSpreadsheetCompiler sc = new ExternalSpreadsheetCompiler();
        String drlstr = sc.compile(ResourceFactory.newClassPathResource(aXlsName).getInputStream(), aWorksheetName,

        ResourceFactory.newClassPathResource(aDrlName).getInputStream(), aStartRow, aStartCol);

        System.out.println(drlstr);
        aKbuilder.add(new ByteArrayResource(drlstr.getBytes()), ResourceType.DRL);
        Iterator<KnowledgeBuilderError> errors = aKbuilder.getErrors().iterator();

        if (errors.hasNext()) {
            StringBuilder errorMsg = new StringBuilder("Error compiling rules:");
            while (errors.hasNext()) {
                errorMsg.append("\t" + errors.next().getMessage());
            }

            System.out.println(errorMsg.toString());
        }
    }
}
