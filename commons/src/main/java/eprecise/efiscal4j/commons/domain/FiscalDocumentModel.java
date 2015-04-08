
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

    private String value;

    private String description;

    private FiscalDocumentModel(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFiscalDocumentModelWithDescription() {
        return this.value + " - " + this.description;
    }

    public static FiscalDocumentModel findByCode(String code) {
        for (final FiscalDocumentModel fiscalDocumentModel : values()) {
            if (fiscalDocumentModel.getValue().equals(code)) {
                return fiscalDocumentModel;
            }
        }
        return null;
    }
}
