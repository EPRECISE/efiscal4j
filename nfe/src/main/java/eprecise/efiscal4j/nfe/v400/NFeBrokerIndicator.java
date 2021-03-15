
package eprecise.efiscal4j.nfe.v400;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Indicador de intermediador/marketplace
 *
 */

@XmlType
@XmlEnum(Integer.class)
public enum NFeBrokerIndicator {

                                @XmlEnumValue("0")
                                OPERACAO_SEM_INTERMEDIADOR(0, "Operação sem intermediador (em site ou plataforma própria)"),
                                @XmlEnumValue("1")
                                OPERACAO_SITE_PLATAFORMA_TERCEIRO(1, "Operação em site ou plataforma de terceiros (intermediadores/marketplace)");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private NFeBrokerIndicator(final int value, final String description) {
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

    public static NFeBrokerIndicator findByCode(final int code) {
        for (final NFeBrokerIndicator presenceIndicator : NFeBrokerIndicator.values()) {
            if (presenceIndicator.getValue() == code) {
                return presenceIndicator;
            }
        }
        return null;
    }

}
