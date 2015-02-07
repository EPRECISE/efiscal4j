/**
 * 
 */

package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identificador do processo de emissão do CT-e
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum IdentifierEmission implements Serializable {
    
    @XmlEnumValue("0") APLICATIVO_CONTRIBUINTE(0, "emissão de CT-e com aplicativo do contribuinte"),
    @XmlEnumValue("1") AVULSA_PELO_FISCO(1, "emissão de CT-e avulsa pelo Fisco"),
    @XmlEnumValue("2") ATRAVES_DO_FISCO(2, "emissão de CT-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco"),
    @XmlEnumValue("3") FORNECIDO_PELO_FISCO(3, "emissão CT-e pelo contribuinte com aplicativo fornecido pelo Fisco");
    
    private static final long serialVersionUID = 1L;
    
    private final int value;
    
    private final String description;
    
    private IdentifierEmission(int value, String description) {
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
