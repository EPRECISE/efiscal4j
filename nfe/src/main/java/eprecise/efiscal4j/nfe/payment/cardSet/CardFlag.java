/**
 * 
 */

package eprecise.efiscal4j.nfe.payment.cardSet;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * Bandeira da operadora de cartão de crédito/débito
 * 
 * @author Fernando Glizt
 * 
 */

@XmlType
@XmlEnum(String.class)
public enum CardFlag implements Serializable {

                                              VISA("01", "Visa"),
                                              MASTERCARD("02", "Mastercard"),
                                              AMERICAN_EXPRESS("03", "American Express"),
                                              SOROCRED("04", "Sorocred"),
                                              DINERS("05", "Diners"),
                                              ELO("06", "Elo"),
                                              HIPERCARD("07", "Hipercard"),
                                              AURA("08", "Aura"),
                                              CABAL("09", "Cabal"),
                                              OUTROS("99", "Outros");

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private CardFlag(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static CardFlag findByCode(String code) {
        for (final CardFlag entity : CardFlag.values()) {
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
