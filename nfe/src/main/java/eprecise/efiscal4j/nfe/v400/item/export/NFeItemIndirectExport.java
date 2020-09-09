
package eprecise.efiscal4j.nfe.v400.item.export;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1104Variable;


/**
 * Exportação indireta
 *
 */
public class NFeItemIndirectExport implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nRE") @NotNull @Pattern(regexp = "[0-9]{0,12}") final String exportRegistrationNumber;

    private @XmlElement(name = "chNFe") @NotNull @Size(max = 44) @NFeAccessKey final String accessKey;

    private @XmlElement(name = "qExport") @NotNull @NFeDecimal1104Variable final String itemExportQuantity;

    public static class Builder {

        private String exportRegistrationNumber;

        private String accessKey;

        private String itemExportQuantity;

        /**
         * Registro de exportação
         * 
         * @param exportRegistrationNumber
         * @return
         */
        public Builder withExportRegistrationNumber(String exportRegistrationNumber) {
            this.exportRegistrationNumber = exportRegistrationNumber;
            return this;
        }

        /**
         * Chave de acesso da NF-e recebida para exportação
         * 
         * @param accessKey
         * @return
         */
        public Builder withAccessKey(String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        /**
         * Quantidade do item efetivamente exportado
         * 
         * @param itemExportQuantity
         * @return
         */
        public Builder withItemExportQuantity(String itemExportQuantity) {
            this.itemExportQuantity = itemExportQuantity;
            return this;
        }

        public NFeItemIndirectExport build() {
            final NFeItemIndirectExport entity = new NFeItemIndirectExport(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeItemIndirectExport() {
        this.exportRegistrationNumber = null;
        this.accessKey = null;
        this.itemExportQuantity = null;
    }

    public NFeItemIndirectExport(final Builder builder) {
        this.exportRegistrationNumber = builder.exportRegistrationNumber;
        this.accessKey = builder.accessKey;
        this.itemExportQuantity = builder.itemExportQuantity;
    }

    public String getExportRegistrationNumber() {
        return exportRegistrationNumber;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getItemExportQuantity() {
        return itemExportQuantity;
    }

}
