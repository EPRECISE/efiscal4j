
package eprecise.efiscal4j.nfe.transport;

import java.io.Serializable;


/**
 * Modalidade do frete
 * 
 * @author Fernando Glizt
 * 
 */
public enum ShippingModality implements Serializable {

                                                      POR_CONTA_EMITENTE(0, "Por conta do emitente"),
                                                      POR_CONTA_DESTINATARIO_REMETENTE(1, "Por conta do destinatário/remetente"),
                                                      POR_CONTA_TERCEIROS(2, "Por conta de terceiros"),
                                                      PROPRIO_POR_CONTA_REMETENTE(3, "Transporte próprio por conta do remetente"),
                                                      PROPRIO_POR_CONTA_DESTINATARIO(4, "Transporte próprio por conta do destinatário"),
                                                      SEM_OCORRENCIA_TRANSPORTE(9, "Sem Ocorrência de transporte");

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
