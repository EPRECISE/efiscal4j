
package eprecise.efiscal4j.nfe.v310.transport;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Modalidade do frete
 * 
 * @author Felipe Bueno
 * 
 */
@XmlType
@XmlEnum(Integer.class)
public enum ShippingModality implements Serializable {

    @XmlEnumValue("0") POR_CONTA_REMETENTE(0, "Contratação do frete por conta do remetente (CIF)"),
    @XmlEnumValue("1") POR_CONTA_DESTINATARIO(1, "Contratação do frete por conta do destinatário (FOB)"),
    @XmlEnumValue("2") POR_CONTA_TERCEIROS(2, "Contratação do frete por conta de terceiro"),
    @XmlEnumValue("9") SEM_FRETE(9, "Sem Ocorrência de transporte");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private ShippingModality(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static ShippingModality findByCode(int code) {
        for (final ShippingModality shippingModality : ShippingModality.values()) {
            if (shippingModality.getValue() == code) {
                return shippingModality;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
