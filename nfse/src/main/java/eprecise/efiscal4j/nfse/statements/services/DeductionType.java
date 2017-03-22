
package eprecise.efiscal4j.nfse.statements.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de dedução: M - Materiais; S - Sub-empreiteras
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(String.class)
public enum DeductionType {

                           @XmlEnumValue("M")
                           MATERIALS("M", "Materiais"),
                           @XmlEnumValue("S")
                           SUBCONTRACTORS("S", "Sub-empreiteras");

    private final String value;

    private final String description;

    private DeductionType(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
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
