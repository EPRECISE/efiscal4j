
package eprecise.efiscal4j.nfe.v310.item.di;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Forma de Importação quanto a intermediação
 * 
 * @author Felipe Bueno
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum IntermediaryImportType implements Serializable {
    //@formatter:off
    @XmlEnumValue("1") POR_CONTA_PROPRIA("1", "Por conta própria"),
    @XmlEnumValue("2") POR_CONTA_E_ORDEM("2", "Por conta e ordem"),
    @XmlEnumValue("3") ENCOMENDA("3", "Encomenda");
    //@formatter:on
    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private IntermediaryImportType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getValueWithDescription() {
        return this.value + " - " + this.description;
    }

    public static IntermediaryImportType findByCode(String code) {
        for (final IntermediaryImportType entity : IntermediaryImportType.values()) {
            if (entity.getValue().equals(code)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
