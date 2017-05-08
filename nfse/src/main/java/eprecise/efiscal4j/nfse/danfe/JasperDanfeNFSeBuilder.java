
package eprecise.efiscal4j.nfse.danfe;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.output.ByteArrayOutputStream;

import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.CompNFSe;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


public class JasperDanfeNFSeBuilder {

    private enum DataSourceType {
                                 ENTITY {

                                     @Override
                                     public JRDataSource generate(final CompNFSe nfse) throws JRException {
                                         return new JRBeanCollectionDataSource(Arrays.asList(nfse));
                                     }
                                 },
                                 XML {

                                     @Override
                                     public JRDataSource generate(final CompNFSe nfse) throws JRException {
                                         return new JRXmlDataSource(new ByteArrayInputStream(new FiscalDocumentSerializer<>(nfse).serialize().getBytes()));
                                     }
                                 };

        public abstract JRDataSource generate(CompNFSe nfse) throws JRException;
    }

    private JasperDanfeNFSeCatalog catalog = new DefaultJasperDanfeNFSeCatalog();

    private JasperDanfeNFSeParamsSource paramsSource = new DefaultJasperDanfeNFSeParamsSource();

    private final Map<String, Object> params = new HashMap<>();

    private final CompNFSe nfse;

    private DataSourceType type = DataSourceType.XML;

    public JasperDanfeNFSeBuilder(final CompNFSe nfse) {
        this.nfse = nfse;
    }

    public JasperDanfeNFSeBuilder usingEntity() {
        type = DataSourceType.ENTITY;
        return this;
    }

    public JasperDanfeNFSeBuilder usingXML() {
        type = DataSourceType.XML;
        return this;
    }

    public JasperDanfeNFSeBuilder withCatalog(final JasperDanfeNFSeCatalog catalog) {
        this.catalog = catalog;
        return this;
    }

    public JasperDanfeNFSeBuilder withParamsSource(final JasperDanfeNFSeParamsSource source) {
        paramsSource = source;
        return this;
    }

    public <T> JasperDanfeNFSeBuilder withParam(final String name, final T value) {
        params.put(name, value);
        return this;
    }

    public JasperPrint build() throws IOException, JRException {
        params.putAll(paramsSource.getParamsOf(nfse));
        return JasperFillManager.fillReport(catalog.get(), params, type.generate(nfse));
    }

    public void toPdf(final OutputStreamSupplier out) throws IOException, JRException {
        final JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(build()));
        final OutputStream outputStream = out.get();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        outputStream.close();
    }

    public void toPdf(final OutputStream out) throws IOException, JRException {
        this.toPdf(() -> out);
    }

    public void toPdf(final File file) throws IOException, JRException {
        final OutputStream out = new FileOutputStream(file);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.toPdf(out);
    }

    public byte[] toPdf() throws IOException, JRException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.toPdf(out);
        return out.toByteArray();
    }

    public void toHtml(final OutputStream out) throws IOException, JRException {
        this.toHtml(() -> out);
    }

    public void toHtml(final OutputStreamSupplier out) throws IOException, JRException {
        final HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(build()));
        final OutputStream outputStream = out.get();
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
        exporter.exportReport();
        outputStream.close();
    }

    public void toHtml(final StringBuffer out) throws IOException, JRException {
        final HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(build()));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
        exporter.exportReport();
    }

    public interface OutputStreamSupplier {

        OutputStream get() throws IOException;
    }
}
