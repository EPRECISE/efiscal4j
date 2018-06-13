
package eprecise.efiscal4j.nfe.payment.cardSet;

import java.io.Serializable;


/**
 * Tipo de Integração do processo de pagamento com o sistema de automação da empresa
 *
 * @author Fernando Glizt
 *
 */
public enum CardSetIntegration implements Serializable {
                                                        INTEGRATED("1", "Pagamento integrado com o sistema de automação da empresa Ex. equipamento TEF , Comercio Eletronico"),
                                                        NON_INTEGRATED("2", "Pagamento não integrado com o sistema de automação da empresa Ex: equipamento POS");

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private CardSetIntegration(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static CardSetIntegration findByCode(String code) {
        for (final CardSetIntegration entity : CardSetIntegration.values()) {
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
