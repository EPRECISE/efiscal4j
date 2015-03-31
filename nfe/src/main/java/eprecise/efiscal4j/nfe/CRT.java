
package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Código de Regime Tributário
 * 
 * @author Felipe Bueno
 * 
 */

@XmlType
@XmlEnum(String.class)
public enum CRT implements Serializable {
    @XmlEnumValue("1") SIMPLES_NACIONAL("1", "Simples Nacional"),
    @XmlEnumValue("2") SIMPLES_NACIONAL_EXCESSO_SUBLIMITE("2", "Simples Nacional – excesso de sublimite de receita bruta"),
    @XmlEnumValue("3") REGIME_NORMAL("3", "Regime Normal");

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private CRT(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCrtWithDescription() {
        return this.value + " - " + this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}
