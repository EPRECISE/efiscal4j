
package eprecise.efiscal4j.nfe.transport;

import java.io.Serializable;


/**
 * Modalidade do frete
 * 
 * @author Fernando Glizt
 * 
 */
public enum ShippingModality implements Serializable {

				BY_ISSUER(0, "Contratação do frete por conta do remetente (CIF)"),
				BY_RECEIVER(1, "Contratação do frete por conta do destinatário (FOB)"),
				BY_THIRD(2, "Contratação do frete por conta de terceiros"),
				OWN_BY_SENDER(3, "Transporte próprio por conta do remetente"),
				OWN_BY_RECEIVER(4, "Transporte próprio por conta do destinatário"),
				WITHOUT_TRANSPORT(9, "Sem Ocorrência de transporte");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private ShippingModality(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static ShippingModality findByCode(int code) {
        for (final ShippingModality shippingModality : ShippingModality.values()) {
            if (shippingModality.getValue() == code) {
                return shippingModality;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
