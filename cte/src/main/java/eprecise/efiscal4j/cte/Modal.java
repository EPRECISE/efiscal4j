/**
 * 
 */

package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Modal
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum Modal implements Serializable {
    
    @XmlEnumValue("01") RODOVIARIO("01", "Rodoviário"),
    @XmlEnumValue("02") AEREO("02", "Aéreo"),
    @XmlEnumValue("03") AQUAVIARIO("03", "Aquaviário"),
    @XmlEnumValue("04") FERROVIARIO("04", "Ferroviário"),
    @XmlEnumValue("05") DUTOVIARIO("05", "Dutoviário"),
    @XmlEnumValue("06") MULTIMODAL("06", "Multimodal");
    
    private static final long serialVersionUID = 1L;
    
    private final String value;
    
    private final String description;
    
    private Modal(String value, String description) {
	this.value = value;
	this.description = description;
    }
    
    public String getValue() {
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
