
package eprecise.efiscal4j.nfse.statements.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Responsável Retenção: 1 - Tomador; 2 - Intermediário
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum ResponsibleRetention {

                                  @XmlEnumValue("1") YES(1, "Tomador"),
                                  @XmlEnumValue("2") NO(2, "Intermediário");

    private final int value;

    private final String description;

    private ResponsibleRetention(final int value, final String description) {
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