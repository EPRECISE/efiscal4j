package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(Integer.class)
public enum TransmissionEnvironmnent {
	
	@XmlEnumValue("1") PRODUCAO(1, "Produção"),
	@XmlEnumValue("2") HOMOLOGACAO(2, "Homologação");
	
    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private TransmissionEnvironmnent(int value, String description) {
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
