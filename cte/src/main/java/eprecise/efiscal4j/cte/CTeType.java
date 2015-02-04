/**
 * 
 */

package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Tipo do CT-e
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum CTeType implements Serializable {
    
    @XmlEnumValue("0") NORMAL(0, "CT-e Normal"),
    @XmlEnumValue("1") COMPLEMENTO_DE_VALORES(1, "CT-e de Complemento de Valores"),
    @XmlEnumValue("2") ANULACAO(2, "CT-e de Anulação"),
    @XmlEnumValue("3") SUBSTITUTO(3, "CT-e Substituto");
    
    private static final long serialVersionUID = 1L;
    
    private final int value;
    
    private final String description;
    
    private CTeType(int value, String description) {
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
