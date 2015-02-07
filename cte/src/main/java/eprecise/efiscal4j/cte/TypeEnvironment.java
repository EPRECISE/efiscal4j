/**
 * 
 */

package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Tipo Ambiente
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum TypeEnvironment implements Serializable {
    
    @XmlEnumValue("1") RETRATO(1, "Produção"),
    @XmlEnumValue("2") PAISAGEM(2, "Homologação");
    
    private static final long serialVersionUID = 1L;
    
    private final int value;
    
    private final String description;
    
    private TypeEnvironment(int value, String description) {
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
