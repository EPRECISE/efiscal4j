/**
 * 
 */

package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Formato de impressão do DACTE
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum PrintFormatDACTE implements Serializable {
    
    @XmlEnumValue("1") RETRATO(1, "Retrato"),
    @XmlEnumValue("2") PAISAGEM(2, "Paisagem");
    
    private static final long serialVersionUID = 1L;
    
    private final int value;
    
    private final String description;
    
    private PrintFormatDACTE(int value, String description) {
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
