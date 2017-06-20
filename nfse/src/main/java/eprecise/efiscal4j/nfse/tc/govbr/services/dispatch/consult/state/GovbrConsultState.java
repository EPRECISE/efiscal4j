
package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.consult.state;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum GovbrConsultState {

                               @XmlEnumValue("1")
                               NOT_RECEIVED(1, "Não Recebido"),
                               @XmlEnumValue("2")
                               NOT_PROCESSED(2, "Não Processado"),
                               @XmlEnumValue("3")
                               PROCESSED_WITH_ERRORS(3, "Processado com erros"),
                               @XmlEnumValue("4")
                               PROCESSED_SUCESSFULLY(4, "Processado com sucesso");

    private final Integer value;

    private final String description;

    private GovbrConsultState(final Integer value, final String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public static GovbrConsultState findByCode(final Integer natureOperation) {
        for (final GovbrConsultState entity : GovbrConsultState.values()) {
            if (entity.getValue().equals(natureOperation)) {
                return entity;
            }
        }
        return null;
    }

}
