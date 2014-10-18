package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicador de presen�a do comprador no estabelecimento comercial no momento da opera��o
 * @author Felipe Bueno
 *
 */

@XmlType
@XmlEnum(Integer.class)
public enum PurchaserPresenceIndicator {

	/**
	 * ex.: Nota Fiscal complementar ou de ajuste
	 */
	@XmlEnumValue("0") NAO_SE_APLICA(0, "N�o se aplica"),
	@XmlEnumValue("1") OPERACAO_PRESENCIAL(1, "Opera��o Presencial"),
	@XmlEnumValue("2") NAO_PRESENCIAL_INTERNET(2, "N�o Presencial - Internet"),
	@XmlEnumValue("3") NAO_PRESENCIAL_TELEATENDIMENTO(3, "N�o Presencial - Teleatendimento"),
	@XmlEnumValue("4") NFCE_ENTREGA_DOMICILIO(4, "NFC-e Entrega a Domic�lio"),
	@XmlEnumValue("9") NAO_PRESENCIAL_OUTROS(9, "N�o Presencial - Outros");	
	

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
