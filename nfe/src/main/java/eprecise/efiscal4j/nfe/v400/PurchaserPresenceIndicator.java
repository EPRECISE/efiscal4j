
package eprecise.efiscal4j.nfe.v400;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Indicador de presença do comprador no estabelecimento comercial no momento da Operação
 * 
 * @author Felipe Bueno
 *
 */

@XmlType
@XmlEnum(Integer.class)
public enum PurchaserPresenceIndicator {

    /**
     * ex.: Nota Fiscal complementar ou de ajuste
     */
    @XmlEnumValue("0") NAO_SE_APLICA(0, "Não se aplica"),
    @XmlEnumValue("1") OPERACAO_PRESENCIAL(1, "Operação Presencial"),
    @XmlEnumValue("2") NAO_PRESENCIAL_INTERNET(2, "Não Presencial - Internet"),
    @XmlEnumValue("3") NAO_PRESENCIAL_TELEATENDIMENTO(3, "Não Presencial - Teleatendimento"),
    @XmlEnumValue("4") NFCE_ENTREGA_DOMICILIO(4, "NFC-e Entrega a Domicílio"),
    @XmlEnumValue("9") NAO_PRESENCIAL_OUTROS(9, "Não Presencial - Outros");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private PurchaserPresenceIndicator(int value, String description) {
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

}
