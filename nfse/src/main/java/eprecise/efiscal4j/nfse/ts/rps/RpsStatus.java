
package eprecise.efiscal4j.nfse.ts.rps;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Status do Rps: 1 - Normal, 2 - Cancelado
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum RpsStatus {

                       @XmlEnumValue("1") NORMAL(1, "Normal"),
                       @XmlEnumValue("2") CANCELED(2, "Cancelado");

    private final int value;

    private final String description;

    private RpsStatus(final int value, final String description) {
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
