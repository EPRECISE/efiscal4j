
package eprecise.efiscal4j.nfe.deliveryDFe;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum NFeDeliveryDfeNFeStatus implements Serializable {
                                                             @XmlEnumValue("1")
                                                             AUTHORIZED(1, "Uso autorizado"),
                                                             @XmlEnumValue("2")
                                                             DENIED(2, "Uso negado"),
                                                             @XmlEnumValue("3")
                                                             CANCELLED(3, "NF-e Cancelada");

    private final int value;

    private final String description;

    NFeDeliveryDfeNFeStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
