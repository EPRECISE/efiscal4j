
package eprecise.efiscal4j.nfse.transmission.response.state;

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
public enum NFSeConsultState {

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

    private NFSeConsultState(final Integer value, final String description) {
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

    public static NFSeConsultState findByCode(final Integer natureOperation) {
        for (final NFSeConsultState entity : NFSeConsultState.values()) {
            if (entity.getValue().equals(natureOperation)) {
                return entity;
            }
        }
        return null;
    }

}
