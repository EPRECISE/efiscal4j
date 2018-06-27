
package eprecise.efiscal4j.nfe.processed;

import java.io.IOException;
import java.util.Date;

import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentSupportedVersion;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ProcessedFiscalDocument {

    private final String id;

    private final FiscalDocumentSupportedVersion version;

    private final String applicationVersion;

    private final String accessKey;

    private final Date processing;

    private final String protocolNumber;

    private final String digestValue;

    private final String statusCode;

    private final String statusDescription;

    private final FiscalDocument document;

    public static class ProcessedFiscalDocumentBuilder {

        public ProcessedFiscalDocument buildFromXml(final String xml) {

            for (FiscalDocumentSupportedVersion v : FiscalDocumentSupportedVersion.values()) {
                try {
                    final FiscalDocumentValidator validator = new FiscalDocumentValidator(this.getClass().getResource(v.getXsdPath()));
                    if (validator.validate(xml).isValid()) {
                        final ProcessedNFeVersion processedNFeVersion = new FiscalDocumentDeserializer<>(xml, v.getProcessedNFeClass()).deserialize();
                        return processedNFeVersion.buildProcessedFiscalDocument();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return null;
        }

    }

}
