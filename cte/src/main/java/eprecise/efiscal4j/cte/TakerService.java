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
public enum TakerService implements Serializable {
    
    @XmlEnumValue("0") REMETENTE("0", "Remetente"),
    @XmlEnumValue("1") EXPEDIDOR("1", "Expedidor"),
    @XmlEnumValue("2") RECEBEDOR("2", "Recebedor"),
    @XmlEnumValue("3") DESTINATARIO("3", "Destinatário");
    private static final long serialVersionUID = 1L;
    
    private final String value;
    
    private final String description;
    
    private TakerService(String value, String description) {
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
