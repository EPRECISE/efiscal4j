
package eprecise.efiscal4j.nfse.tc.elotech.statements.services;

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
public enum ElotechResponsibleRetention {

                                  @XmlEnumValue("1") TAKER(1, "Tomador"),
                                  @XmlEnumValue("2") INTERMEDIARY(2, "Intermediário");

    private final int value;

    private final String description;

    private ElotechResponsibleRetention(final int value, final String description) {
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
