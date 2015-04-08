
package eprecise.efiscal4j.commons.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum FiscalDocumentModel {
    @XmlEnumValue("55") NFE("55", "NF-e"),
    @XmlEnumValue("57") CTE("57", "CT-e"),
    @XmlEnumValue("65") NFCE("65", "NFC-e");

    private static final long serialVersionUID = 1L;

    private String code;

    private String description;

    private FiscalDocumentModel(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
