
package eprecise.efiscal4j.nfe.v400.deliveryDFe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeDeliveryDFeNSU;


/**
 * tag: consNSU Grupo para consultar um DF-e a partir de um NSU específico
 * 
 * @author clecius
 *
 */
public class NFeQueryNSU implements NFeDeliveryDFeRequestType {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "NSU") @NotNull @Size(max = 15) @NFeDeliveryDFeNSU final String nsu;

    public static class Builder {

        private String nsu;

        /**
         * Número Sequencial Único. Geralmente esta consulta será utilizada quando identificado pelo interessado um NSU faltante. O Web Service retornará o documento ou informará que o NSU não existe
         * no Ambiente Nacional. Assim, esta consulta fechará a lacuna do NSU identificado como faltante.
         */
        public Builder withNsu(String nsu) {
            this.nsu = nsu;
            return this;
        }

        public NFeQueryNSU build() {
            final NFeQueryNSU entity = new NFeQueryNSU(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeQueryNSU() {
        this.nsu = null;
    }

    public NFeQueryNSU(Builder builder) {
        this.nsu = builder.nsu;
    }

    public String getNsu() {
        return this.nsu;
    }
}
