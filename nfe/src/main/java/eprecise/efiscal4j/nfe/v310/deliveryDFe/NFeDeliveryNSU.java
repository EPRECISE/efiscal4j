
package eprecise.efiscal4j.nfe.v310.deliveryDFe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeDeliveryDFeNSU;


public class NFeDeliveryNSU implements NFeDeliveryDFeRequestType {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "ultNSU") @NotNull @Size(max = 15) @NFeDeliveryDFeNSU final String lastNsu;

    public static class Builder {

        private String lastNsu;

        /**
         * Último NSU recebido pelo ator. Caso seja informado com zero, ou com um NSU muito antigo, a consulta retornará unicamente as informações resumidas e documentos fiscais eletrônicos que tenham
         * sido recepcionados pelo Ambiente Nacional nos últimos 3 meses.
         */
        public Builder withLastNsu(String lastNsu) {
            this.lastNsu = lastNsu;
            return this;
        }

        public Builder withoutLastNsu() {
            this.lastNsu = "0";
            return this;
        }

        public NFeDeliveryNSU build() {
            final NFeDeliveryNSU entity = new NFeDeliveryNSU(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDeliveryNSU() {
        this.lastNsu = null;
    }

    private NFeDeliveryNSU(Builder builder) {
        this.lastNsu = builder.lastNsu;
    }

    public String getLastNsu() {
        return this.lastNsu;
    }
}
