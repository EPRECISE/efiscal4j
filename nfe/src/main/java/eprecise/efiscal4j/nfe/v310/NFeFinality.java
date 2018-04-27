
package eprecise.efiscal4j.nfe.v310;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(Integer.class)
public enum NFeFinality {

    @XmlEnumValue("1") NORMAL(1, "Normal"),
    @XmlEnumValue("2") COMPLEMENTAR(2, "Complementar"),
    @XmlEnumValue("3") AJUSTE(3, "Ajuste"),
    @XmlEnumValue("4") DEVOLUCAO_RETORNO(4, "Devolução/Retorno");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private NFeFinality(int value, String description) {
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
