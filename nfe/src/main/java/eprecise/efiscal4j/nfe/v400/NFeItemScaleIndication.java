/**
 * 
 */

package eprecise.efiscal4j.nfe.v400;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicador de Escala Relevante
 * 
 * @author Fernando Glizt
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum NFeItemScaleIndication implements Serializable {
    
    @XmlEnumValue("S") SIM("S", "Sim"),
    @XmlEnumValue("N") NAO("N", "Não");
    
    private static final long serialVersionUID = 1L;
    
    private final String value;
    
    private final String description;
    
    private NFeItemScaleIndication(String value, String description) {
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
