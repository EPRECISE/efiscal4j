
package eprecise.efiscal4j.nfse.tc.govbr.v203.cancel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * Codigo do Cancelamento da NFS-e
 *
 * @author Eduardo Costa Basso
 *
 */
@XmlType
@XmlEnum(String.class)
@RequiredArgsConstructor
public enum GovbrCancellationCode implements NFSeCancellationCode {
                                                                   @XmlEnumValue("1")
                                                                   EMISSION_ERROR("1", "Erro de emissão"),
                                                                   @XmlEnumValue("2")
                                                                   SERVICE_NOT_PERFORMED("2", "Serviço não prestado"),
                                                                   @XmlEnumValue("3")
                                                                   SIGNATURE_ERROR("3", "Erro de assinatura"),
                                                                   @XmlEnumValue("4")
                                                                   DUPLICITY("4", "Duplicidade da nota"),
                                                                   @XmlEnumValue("5")
                                                                   PROCESSING_ERROR("5", "Erro de processamento");

    private @Getter final String value;

    private @Getter final String description;

    @Override
    public String getCode() {
        return this.value;
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
