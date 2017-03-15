
package eprecise.efiscal4j.nfse.statements.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * ISS Retido: 1 - Sim 2 - Não
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum IssWithheld {

                         @XmlEnumValue("1") YES(1, "Sim"),
                         @XmlEnumValue("2") NO(2, "Não");

    private final int value;

    private final String description;

    private IssWithheld(final int value, final String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
