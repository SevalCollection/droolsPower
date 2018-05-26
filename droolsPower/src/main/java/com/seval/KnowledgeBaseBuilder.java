package com.seval;

import java.io.IOException;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;

public class KnowledgeBaseBuilder {

  public static KnowledgeBase build(List<RuleMap<String, Object>> ruleAttributes) throws IOException {

    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

    Resource rules = loadRuleFile("product.drl");
    rules = TemplateExpander.expand(rules, ruleAttributes);
    kbuilder.add(rules, ResourceType.DRL);

    // handle errors
    if (kbuilder.hasErrors()) {
      for (KnowledgeBuilderError err : kbuilder.getErrors()) {
        System.out.println("Errors: " + err);
      }
      throw new IllegalArgumentException("Could not parse knowledge.");
    }

    kbase = KnowledgeBaseFactory.newKnowledgeBase();
    // add packages to the knowledgebase
    kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

    return kbase;

  }

  protected static Resource loadRuleFile(String ruleFile) {
    if (ruleFile.contains("://")) {
      return ResourceFactory.newUrlResource(ruleFile);
    } else {
      return ResourceFactory.newClassPathResource(ruleFile, KnowledgeBaseBuilder.class);
    }
  }

}

