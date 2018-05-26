package com.seval;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
 
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.template.ObjectDataCompiler;
 
public final class TemplateExpander {
   
    private TemplateExpander() {}
 
    public static Resource expand(Resource template, Collection<?> rules) throws IOException {
 
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(rules, template.getInputStream());
        Reader rdr = new StringReader(drl);
        return ResourceFactory.newReaderResource(rdr);
    }
}
 