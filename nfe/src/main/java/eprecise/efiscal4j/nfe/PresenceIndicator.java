
package eprecise.efiscal4j.nfe;

/**
 * Indicador de presença do comprador no estabelecimento comercial no momento da Operação
 * 
 */

public enum PresenceIndicator {

    NOT_APPLICABLE(0, "Não se aplica"),
    PRESENCIAL_OPERATION(1, "Operação Presencial"),
    NOT_PRESENCIAL_INTERNET(2, "Não Presencial - Internet"),
    NOT_PRESENCIAL_CALLCENTER(3, "Não Presencial - Teleatendimento"),
    NFCE_HOME_DELIVERY(4, "NFC-e Entrega a Domicílio"),
    PRESENCIAL_OPERATION_OUTSIDE_ESTABLISHMENT(5, "Operação presencial, fora do estabelecimento"),
    NOT_PRESENCIAL_OTHERS(9, "Não Presencial - Outros");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private PresenceIndicator(int value, String description) {
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

    public static PresenceIndicator findByCode(int code) {
        for (final PresenceIndicator presenceIndicator : PresenceIndicator.values()) {
            if (presenceIndicator.getValue() == code) {
                return presenceIndicator;
            }
        }
        return null;
    }

}
