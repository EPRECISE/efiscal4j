
package eprecise.efiscal4j.nfse.tc.govbr.v100.cancel;

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
                                                                   CODE1("1", "Dados do serviço não cadastrados para o prestador"),
                                                                   @XmlEnumValue("2")
                                                                   CODE2("2", "Serviço informado não permite dedução na base de cálculo"),
                                                                   @XmlEnumValue("3")
                                                                   CODE3("3", "Outros"),
                                                                   @XmlEnumValue("4")
                                                                   CODE4("4", "Serviço informado não premite tributação fora do município"),
                                                                   @XmlEnumValue("5")
                                                                   CODE5("5", "Valor do ISS retido diferente do ISS devido"),
                                                                   @XmlEnumValue("6")
                                                                   CODE6("6", "Endereço informado é de fora do município"),
                                                                   @XmlEnumValue("7")
                                                                   CODE7("7", "Tomador não cadastrado na base de dados de CCM"),
                                                                   @XmlEnumValue("8")
                                                                   CODE8("8", "Inscrição municipal do tomador cancelada"),
                                                                   @XmlEnumValue("9")
                                                                   CODE9("9", "E-mail do tomador inválido");

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
