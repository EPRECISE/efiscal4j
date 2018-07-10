
package eprecise.efiscal4j.nfe.item.di;

import java.io.Serializable;


/**
 * Forma de Importação quanto a intermediação
 * 
 * @author Fernando Glizt
 * 
 */
public enum IntermediaryImportType implements Serializable {
    //@formatter:off
	    SELF_IMPORT_MEDIATION("1", "Por conta própria"),
	    IMPORT_MEDIATION_AND_ORDER("2", "Por conta e ordem"),
	    IMPORT_ORDER("3", "Encomenda");
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
