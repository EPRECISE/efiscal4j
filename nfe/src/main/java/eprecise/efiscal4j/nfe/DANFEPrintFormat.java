
package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum DANFEPrintFormat {
    @XmlEnumValue("0") SEM_DANFE("0", "Sem DANFe"),
    @XmlEnumValue("1") DANFE_RETRATO("1", "DANFe Retrato"),
    @XmlEnumValue("2") DANFE_PAISAGEM("2", "DANFe Paisagem"),
    @XmlEnumValue("3") DANFE_SIMPLIFICADO("3", "DANFe Simplificado"),
    @XmlEnumValue("4") DANFE_NFCE("4", "DANFe NFC-e"),
    @XmlEnumValue("5") DANFE_NFCE_MENSAGEM_ELETRONICA("5", "DANFe NFC-e em mensagem eletrônica");

    private static final long serialVersionUID = 1L;

    private String value;

    private String description;

    private DANFEPrintFormat(String value, String description) {
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
