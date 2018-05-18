/**
 * 
 */

package eprecise.efiscal4j.nfe.v400.refdocuments;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Código do modelo do Documento Fiscal. Utilizar 01 para NF modelo 1/1A e 02 para NF modelo 02
 * 
 * @author Fernando Glizt
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum ReferencedNFModel implements Serializable {
    
    @XmlEnumValue("01") MODELO_01_1A("01", "Modelo 1/1A"),
    @XmlEnumValue("02") MODELO_02("02", "Modelo 02");
    
    private static final long serialVersionUID = 1L;
    
    private final String value;
    
    private final String description;
    
    private ReferencedNFModel(String value, String description) {
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
