
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Indicador de processamento síncrono.
 * 
 * @author Felipe Bueno
 * 
 */

@XmlType
@XmlEnum(String.class)
public enum SynchronousProcessing implements Serializable {
    @XmlEnumValue("0") ASSINCRONO("0", "Não - Assíncrono"),
    @XmlEnumValue("1") SINCRONO("1", "Sim - Síncrono");

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private SynchronousProcessing(String value, String description) {
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
