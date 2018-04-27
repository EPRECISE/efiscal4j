
package eprecise.efiscal4j.nfe.v400;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum DestinationOperationIdentifier {

    @XmlEnumValue("1") INTERNA("1", "Interna"),
    @XmlEnumValue("2") INTERESTADUAL("2", "Interestadual"),
    @XmlEnumValue("3") EXTERIOR("3", "Exterior");

    private static final long serialVersionUID = 1L;

    private String value;

    private String description;

    private DestinationOperationIdentifier(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

}
