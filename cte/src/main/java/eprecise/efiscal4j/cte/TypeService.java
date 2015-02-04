/**
 * 
 */

package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Tipo do Serviço
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum TypeService implements Serializable {
    
    @XmlEnumValue("0") NORMAL("0", "Normal"),
    @XmlEnumValue("1") SUBCONTRATACAO("1", "Subcontratação"),
    @XmlEnumValue("2") REDESPACHO("2", "Redespacho"),
    @XmlEnumValue("3") REDESPACHO_INTERMEDIARIO("3", "Redespacho Intermediário"),
    @XmlEnumValue("4") SERVICO_VINCULADO_A_MULTINACIONAL("4", "Serviço Vinculado a Multimodal");
    
    private static final long serialVersionUID = 1L;
    
    private final String value;
    
    private final String description;
    
    private TypeService(String value, String description) {
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
