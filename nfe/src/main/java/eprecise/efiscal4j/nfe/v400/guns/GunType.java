
package eprecise.efiscal4j.nfe.v400.guns;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(Integer.class)
public enum GunType {

    @XmlEnumValue("0") ALLOWED_USE(0, "Uso permitido"),
    @XmlEnumValue("1") RESTRICTED_USE(1, "Uso restrito");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private GunType(int value, String description) {
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
