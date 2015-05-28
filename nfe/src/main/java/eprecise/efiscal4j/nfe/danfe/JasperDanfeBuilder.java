
package eprecise.efiscal4j.nfe.danfe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import eprecise.efiscal4j.nfe.NFe;


public class JasperDanfeBuilder {

    private JasperDanfeCatalog catalog;

    private final Map<String, Object> params = new HashMap<>();

    private final NFe nfe;

    public JasperDanfeBuilder(NFe nfe) {
        this.nfe = nfe;
    }

    public <T> JasperDanfeBuilder withCatalog(JasperDanfeCatalog catalog) {
        this.catalog = catalog;
        return this;
    }

    public <T> JasperDanfeBuilder withParam(String name, T value) {
        this.params.put(name, value);
        return this;
    }

    public JasperPrint build() throws IOException, JRException {
        return JasperFillManager.fillReport(this.catalog.get(this.nfe.getNFeInfo().getnFeIdentification().getDanfePrintFormat()), this.params, new JRBeanCollectionDataSource(Arrays.asList(this.nfe)));
    }

    public void toPdf(OutputStream out) throws IOException, JRException {
        final JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(this.build()));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.exportReport();
    }

    public void toPdf(File file) throws IOException, JRException {
        final OutputStream out = new FileOutputStream(file);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.toPdf(out);
    }

    public void toHtml(OutputStream out) throws IOException, JRException {
        final HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(this.build()));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
        exporter.exportReport();
    }

    public void toHtml(StringBuffer out) throws IOException, JRException {
        final HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(this.build()));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
        exporter.exportReport();
    }
}
