
package eprecise.efiscal4j.nfe.emitter;

import java.io.Serializable;


/**
 * Código de Regime Tributário
 * 
 * @author Fernando Glizt
 * 
 */
public enum CRT implements Serializable {
                                         SIMPLE_NATIONAL("1", "Simples Nacional"),
                                         SIMPLE_NATIONAL_WITH_SUBLIME_EXCESS("2", "Simples Nacional – excesso de sublimite de receita bruta"),
                                         NORMAL_REGIME("3", "Regime Normal");

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

    public static CRT findByCode(String code) {
        for (final CRT crt : values()) {
            if (crt.getValue().equals(code)) {
                return crt;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}
