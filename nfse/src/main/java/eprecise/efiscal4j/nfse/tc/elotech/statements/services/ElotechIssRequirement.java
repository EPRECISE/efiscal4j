
package eprecise.efiscal4j.nfse.tc.elotech.statements.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Exigibilidade ISS
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum ElotechIssRequirement {

                            @XmlEnumValue("1") REQUIRED(1, "Exigível"),
                            @XmlEnumValue("2") NO_INCIDENCE(2, "Não incidência"),
                            @XmlEnumValue("3") EXEMPTION(3, "Isenção"),
                            @XmlEnumValue("4") EXPORTATION(4, "Exportação"),
                            @XmlEnumValue("5") IMMUNITY(5, "Imunidade"),
                            @XmlEnumValue("6") SUSPENDED_BY_JUDICIAL_DECISION(6, "Suspensa por decisão judicial");

    private final int value;

    private final String description;

    private ElotechIssRequirement(final int value, final String description) {
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
