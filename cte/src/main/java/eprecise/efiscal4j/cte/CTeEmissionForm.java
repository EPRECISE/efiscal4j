package eprecise.efiscal4j.cte;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Forma de emissao da NF-e
 * 
 * @author Carlos Gomes
 *
 */

@XmlType
@XmlEnum(Integer.class)
public enum CTeEmissionForm {
    @XmlEnumValue("1") NORMAL(1, "Normal"),
    @XmlEnumValue("4") CONTINGENCIA_FS(4, "EPEC pela SVC"),
    @XmlEnumValue("5") CONTINGENCIA_SCAN(5, "Contingência FSDA"),
    @XmlEnumValue("7") CONTINGENCIA_DPEC(7, "Autorização pela SVC-RS"),
    @XmlEnumValue("8") CONTINGENCIA_FSDA(8, "Autorização pela SVC-SP");
    
    private static final long serialVersionUID = 1L;
    
    private final int value;
    
    private final String description;
    
    private CTeEmissionForm(int value, String description) {
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
