
package eprecise.efiscal4j.nfe.v310;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(Integer.class)
public enum StateRegistrationReceiverIndicator implements Serializable {
    @XmlEnumValue("1") CONTRIBUINTE_ICMS(1, "Contribuinte ICMS - pagamento à vista"),
    @XmlEnumValue("2") ISENTO(2, "Contribuinte isento de inscrição"),
    @XmlEnumValue("9") NAO_CONTRIBUINTE(9, "Não Contribuinte");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private StateRegistrationReceiverIndicator(int value, String description) {
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
        return this.getDescription();
    }
}
