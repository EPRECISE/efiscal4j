/**
 * 
 */

package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicador se o Recebedor retira no Aeroporto, Filial, Porto ou Estação de
 * Destino?
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum IndicatorWithdrawal implements Serializable {
    
    @XmlEnumValue("0") SIM("0", "Sim"),
    @XmlEnumValue("1") NAO("1", "Não");
    
    private static final long serialVersionUID = 1L;
    
    private final String value;
    
    private final String description;
    
    private IndicatorWithdrawal(String value, String description) {
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
