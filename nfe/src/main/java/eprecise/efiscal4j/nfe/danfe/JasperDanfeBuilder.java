
package eprecise.efiscal4j.nfe.danfe;

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
import eprecise.efiscal4j.nfe.v400.person.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.v400.person.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;
import eprecise.efiscal4j.nfe.version.ProcessedNFeVersion;
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


public class JasperDanfeBuilder {

    private enum DataSourceType {
                                 ENTITY {

                                     @Override
                                     public JRDataSource generate(final ProcessedNFeVersion nfe) throws JRException {
                                         return new JRBeanCollectionDataSource(Arrays.asList(nfe));
                                     }
                                 },
                                 XML {

                                     @Override
                                     public JRDataSource generate(final ProcessedNFeVersion nfe) throws JRException {
                                         return new JRXmlDataSource(new ByteArrayInputStream(
                                                 new FiscalDocumentSerializer<>(nfe).considering(LegalEntityDocuments.class, NaturalPersonDocuments.class).serialize().getBytes()));
                                     }
                                 };

        public abstract JRDataSource generate(ProcessedNFeVersion nfe) throws JRException;
    }

    private JasperDanfeCatalog catalog;

    private JasperDanfeParamsSource paramsSource;

    private final Map<String, Object> params = new HashMap<>();

    private final ProcessedNFeVersion nfe;

    private DataSourceType type = DataSourceType.XML;

    public JasperDanfeBuilder(final ProcessedNFeVersion nfe) {
        this.nfe = nfe;
        final FiscalDocumentSupportedVersion version = this.nfe.buildProcessedFiscalDocument().getVersion();
        try {
            this.catalog = version.getJasperDanfeCatalogClass().newInstance();
            this.paramsSource = version.getJasperDanfeParamSourceClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public JasperDanfeBuilder usingEntity() {
        this.type = DataSourceType.ENTITY;
        return this;
    }

    public JasperDanfeBuilder usingXML() {
        this.type = DataSourceType.XML;
        return this;
    }

    public JasperDanfeBuilder withCatalog(final JasperDanfeCatalog catalog) {
        this.catalog = catalog;
        return this;
    }

    public JasperDanfeBuilder withParamsSource(final JasperDanfeParamsSource source) {
        this.paramsSource = source;
        return this;
    }

    public <T> JasperDanfeBuilder withParam(final String name, final T value) {
        this.params.put(name, value);
        return this;
    }

    public JasperPrint build() throws IOException, JRException {
        this.params.putAll(this.paramsSource.getParamsOf(this.nfe));
        return JasperFillManager.fillReport(this.catalog.get(this.nfe.getDanfePrintFormat()), this.params, this.type.generate(this.nfe));
    }

    public void toPdf(final OutputStreamSupplier out) throws IOException, JRException {
        final JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(this.build()));
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
        exporter.setExporterInput(new SimpleExporterInput(this.build()));
        final OutputStream outputStream = out.get();
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
        exporter.exportReport();
        outputStream.close();
    }

    public void toHtml(final StringBuffer out) throws IOException, JRException {
        final HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(this.build()));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
        exporter.exportReport();
    }

    public interface OutputStreamSupplier {

        OutputStream get() throws IOException;
    }
}
