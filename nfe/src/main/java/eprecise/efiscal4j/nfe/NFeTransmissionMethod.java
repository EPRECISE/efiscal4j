package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Forma de emiss�o da NF-e
 * @author Felipe Bueno
 *
 */

@XmlType
@XmlEnum(Integer.class)
public enum NFeTransmissionMethod {
	@XmlEnumValue("1") NORMAL(1, "Normal"),
	@XmlEnumValue("2") CONTINGENCIA_FS(2, "Conting�ncia FS"),
	@XmlEnumValue("3") CONTINGENCIA_SCAN(3, "Conting�ncia SCAN"),
	@XmlEnumValue("4") CONTINGENCIA_DPEC(4, "Conting�ncia DPEC"),
	@XmlEnumValue("5") CONTINGENCIA_FSDA(5, "Conting�ncia FSDA"),
	@XmlEnumValue("6") CONTINGENCIA_SVC_AN(6, "Conting�ncia SVC - AN"),
	@XmlEnumValue("7") CONTINGENCIA_SVC_RS(7, "Conting�ncia SVC - RS"),
	@XmlEnumValue("9") CONTINGENCIA_OFF_LINE_NFCE(9, "Conting�ncia off-line NFC-e");

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
