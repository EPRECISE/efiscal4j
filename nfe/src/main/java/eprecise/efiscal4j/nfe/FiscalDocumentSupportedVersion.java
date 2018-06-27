
package eprecise.efiscal4j.nfe;

import eprecise.efiscal4j.nfe.processed.ProcessedNFeVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 
 * @author Fernando Glizt
 *
 */

@AllArgsConstructor
@Getter
public enum FiscalDocumentSupportedVersion {

                                            VERSION_4_00("4.00", eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFe.class, eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFe.XSD),
                                            VERSION_3_10("3.10", eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFe.class, eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFe.XSD);

    private final String value;

    private final Class<? extends ProcessedNFeVersion> processedNFeClass;

    private final String xsdPath;

    public String getFormattedValue() {
        return this.value.replace('.', '_');
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
