package satyamarcade.b19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XslApplierOnXml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		xsl("PropertApplier.xml","output.html","PropertyApplier.xsl");
		System.out.println("Done");
	}
	
	// This method applies the xslFilename to inFilename and writes
    // the output to outFilename.
    public static void xsl(String inFilename, String outFilename, String xslFilename) {
        try {
            // Create transformer factory
            //TransformerFactory factory = TransformerFactory.newInstance();
        	TransformerFactory factory = new net.sf.saxon.TransformerFactoryImpl();
            // Use the factory to create a template containing the xsl file
            Templates template = factory.newTemplates(new StreamSource(
                new FileInputStream(xslFilename)));

            // Use the template to create a transformer
            Transformer xformer = template.newTransformer();

            // Prepare the input and output files
            Source source = new StreamSource(new FileInputStream(inFilename));
            Result result = new StreamResult(new FileOutputStream(outFilename));

            // Apply the xsl file to the source file and write the result to the output file
            xformer.transform(source, result);
        } catch (FileNotFoundException e) {
        	System.out.println(e.getMessage());
        } catch (TransformerConfigurationException e) {
            // An error occurred in the XSL file
        } catch (TransformerException e) {
            // An error occurred while applying the XSL file
            // Get location of error in input file
            SourceLocator locator = e.getLocator();
            int col = locator.getColumnNumber();
            int line = locator.getLineNumber();
            String publicId = locator.getPublicId();
            String systemId = locator.getSystemId();
        }
    }

}
