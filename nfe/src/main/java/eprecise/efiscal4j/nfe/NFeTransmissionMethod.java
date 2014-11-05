
package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Forma de emissao da NF-e
 * 
 * @author Felipe Bueno
 *
 */

@XmlType
@XmlEnum(Integer.class)
public enum NFeTransmissionMethod {
    @XmlEnumValue("1") NORMAL(1, "Normal"),
    @XmlEnumValue("2") CONTINGENCIA_FS(2, "Contingência FS"),
    @XmlEnumValue("3") CONTINGENCIA_SCAN(3, "Contingência SCAN"),
    @XmlEnumValue("4") CONTINGENCIA_DPEC(4, "Contingência DPEC"),
    @XmlEnumValue("5") CONTINGENCIA_FSDA(5, "Contingência FSDA"),
    @XmlEnumValue("6") CONTINGENCIA_SVC_AN(6, "Contingência SVC - AN"),
    @XmlEnumValue("7") CONTINGENCIA_SVC_RS(7, "Contingência SVC - RS"),
    @XmlEnumValue("9") CONTINGENCIA_OFF_LINE_NFCE(9, "Contingência off-line NFC-e");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private NFeTransmissionMethod(int value, String description) {
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
