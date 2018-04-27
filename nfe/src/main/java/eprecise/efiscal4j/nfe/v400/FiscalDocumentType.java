
package eprecise.efiscal4j.nfe.v400;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum FiscalDocumentType {
    @XmlEnumValue("0") ENTRADA("0", "Entrada"),
    @XmlEnumValue("1") SAIDA("1", "Saida");

    private static final long serialVersionUID = 1L;

    private String type;

    private String description;

    private FiscalDocumentType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

}
