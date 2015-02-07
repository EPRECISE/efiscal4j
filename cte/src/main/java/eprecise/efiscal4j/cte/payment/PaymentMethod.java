/**
 * 
 */

package eprecise.efiscal4j.cte.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Forma de Pagamento
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum PaymentMethod implements Serializable {
    
    @XmlEnumValue("0") PAGO("Pago"),
    @XmlEnumValue("1") A_PAGAR("A pagar"),
    @XmlEnumValue("2") OUTROS("Outros");
    
    private static final long serialVersionUID = 1L;
    
    private final String description;
    
    private PaymentMethod(String description) {
	this.description = description;
    }
    
    public String getDescription() {
	return this.description;
    }
    
    @Override
    public String toString() {
	return this.getDescription();
    }
    
}
