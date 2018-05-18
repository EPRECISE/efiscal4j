
package eprecise.efiscal4j.nfe.v400.transport;

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

    @XmlEnumValue("0") POR_CONTA_EMITENTE(0, "Por conta do emitente"),
    @XmlEnumValue("1") POR_CONTA_DESTINATARIO_REMETENTE(1, "Por conta do destinatário/remetente"),
    @XmlEnumValue("2") POR_CONTA_TERCEIROS(2, "Por conta de terceiros"),
    
    @XmlEnumValue("3") PROPRIO_POR_CONTA_REMETENTE(3, "Transporte próprio por conta do remetente"),
    @XmlEnumValue("4") PROPRIO_POR_CONTA_DESTINATARIO(4, "Transporte próprio por conta do destinatário"),
    
    @XmlEnumValue("9") SEM_OCORRENCIA_TRANSPORTE(9, "Sem Ocorrência de transporte");

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
