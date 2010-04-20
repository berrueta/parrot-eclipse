package es.ctic.parrot.parrot_eclipse.utils;

//import java.io.InputStream;

/*
import es.ctic.parrot.appserv.ParrotAppServ;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.jena.JenaOWLReader;
import es.ctic.parrot.reader.naiverifxml.RifXmlReader;
*/

public class ParrotCoreWrapper {
	
	/*
	private ParrotAppServ app;
	private InputStream template;
	*/
	
	public ParrotCoreWrapper() {
		/*
		this.app = new ParrotAppServ();
        DocumentReader ontologyWrapper = new JenaOWLReader();
        DocumentReader ruleWrapper = new RifXmlReader(ontologyWrapper);
        this.app.setOntologyWrapper(ontologyWrapper);
        this.app.setRuleWrapper(ruleWrapper);
        this.template = Thread.currentThread().getContextClassLoader().getResourceAsStream(template);
        */
	}
	
	// TODO
	public String exec(String input){
		// To debug stream
		System.out.println("Parrot Core Input:" + input);
		return "<html><body>Handler Test</body></html>";
	}
		
}
