package es.ctic.parrot.parrot_eclipse.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


import es.ctic.parrot.appserv.ParrotAppServ;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.StringInput;
import es.ctic.parrot.reader.jena.JenaOWLReader;
import es.ctic.parrot.reader.naiverifxml.RifXmlReader;
import es.ctic.parrot.DocumentaryProject;


public class ParrotCoreWrapper {
	
	private ParrotAppServ app;
	private InputStream template;
	
	public ParrotCoreWrapper() {
		
	}
	
	public String exec(String input, String contenttype){
		app = new ParrotAppServ();
        DocumentReader ontologyWrapper = new JenaOWLReader();
        DocumentReader ruleWrapper = new RifXmlReader(ontologyWrapper);
        app.setOntologyWrapper(ontologyWrapper);
        app.setRuleWrapper(ruleWrapper);
        template = Thread.currentThread().getContextClassLoader().getResourceAsStream("html/template.vm");
		// To debug stream
		System.out.println("Parrot Core Input:" + input);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DocumentaryProject dp = new DocumentaryProject(template, outputStream, "EN");
		dp.addInput(new StringInput(input, contenttype));
		
		try {
			app.createDocumentation(dp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return outputStream.toString();
	}
		
}
