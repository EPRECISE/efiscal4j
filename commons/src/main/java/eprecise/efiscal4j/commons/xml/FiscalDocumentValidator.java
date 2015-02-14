package eprecise.efiscal4j.commons.xml;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class FiscalDocumentValidator {
    private final Schema schema;
    
    public FiscalDocumentValidator(URL xsd) throws IOException {
	final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	try {
	    this.schema = factory.newSchema(xsd);
	} catch (final SAXException e) {
	    throw new RuntimeException(e);
	}
    }
    
    public ValidationResult validate(String xml) {
	final Validator validator = this.schema.newValidator();
	try {
	    validator.validate(new StreamSource(new StringReader(xml)));
	    return new ValidationResult();
	} catch (final SAXException e) {
	    return new ValidationResult(e);
	} catch (final IOException e) {
	    throw new RuntimeException(e);
	}
    }
    
    public static class ValidationResult {
	public String e = "";
	
	public ValidationResult() {
	}
	
	public ValidationResult(Exception error) {
	    this.e = error.getMessage();
	}
	
	public boolean isValid() {
	    return this.e.equals("");
	}
	
	public String getError() {
	    return this.e;
	}
    }
    
}
