
package eprecise.efiscal4j.nfe.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de Integração do processo de pagamento com o sistema de automação da empresa
 *
 * @author Felipe Bueno
 *
 */
@XmlType
@XmlEnum(String.class)
public enum PaymentIntegrationType implements Serializable {
//@formatter:off
    @XmlEnumValue("1") INTEGRADO("1", "Pagamento integrado com o sistema de automação da empresa Ex. equipamento TEF , Comercio Eletronico"),
    @XmlEnumValue("2") NAO_INTEGRADO("2", "Pagamento não integrado com o sistema de automação da empresa Ex: equipamento POS");
//@formatter:on
    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private PaymentIntegrationType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static PaymentIntegrationType findByCode(String code) {
        for (final PaymentIntegrationType entity : PaymentIntegrationType.values()) {
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
