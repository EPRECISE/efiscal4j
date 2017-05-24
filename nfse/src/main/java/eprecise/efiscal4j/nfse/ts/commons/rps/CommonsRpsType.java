
package eprecise.efiscal4j.nfse.ts.commons.rps;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo do Rps
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum CommonsRpsType {

                     @XmlEnumValue("1") PROVISIONAL_SERVICE_RECEIPT(1, "Recibo Provisório de Serviços"),
                     @XmlEnumValue("2") RPS_NF_CONJUGATED(2, "RPS Nota Fiscal Conjugada (Mista)"),
                     @XmlEnumValue("3") COUPON(3, "Cupom");

    private final int value;

    private final String description;

    private CommonsRpsType(final int value, final String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
