
package eprecise.efiscal4j.nfse.tc.govbr.cancel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationCode;


/**
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(String.class)
public enum GovbrCancellationCode implements NFSeCancellationCode {

                                                               @XmlEnumValue("1")
                                                               EMISSION_ERROR("1", "Erro de emissão"),
                                                               @XmlEnumValue("2")
                                                               SERVICE_NOT_PERFORMED("2", "Serviço não prestado"),
                                                               @XmlEnumValue("3")
                                                               SIGNATURE_ERROR("3", "Erro de assinatura"),
                                                               @XmlEnumValue("4")
                                                               DUPLICITY("4", "Duplicidade"),
                                                               @XmlEnumValue("5")
                                                               PROCESSING_ERROR("5", "Erro de processamento");

    private final String value;

    private final String description;

    private GovbrCancellationCode(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getCode() {
        return value;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public static GovbrCancellationCode findByCode(final String code) {
        for (final GovbrCancellationCode entity : GovbrCancellationCode.values()) {
            if (entity.getValue().equals(code)) {
                return entity;
            }
        }
        return null;
    }

}
