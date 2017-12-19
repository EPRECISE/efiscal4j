
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;
import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;


/**
 * Tipo de Evento
 * 
 * @author Felipe Bueno
 *
 */
@XmlType
@XmlEnum(String.class)
public enum EventType implements Serializable {
                                               @XmlEnumValue("210200")
                                               CONFIRMACAO_OPERACAO("210200", "", "Confirmação da Operação"),
                                               @XmlEnumValue("210210")
                                               CIENCIA_OPERACAO("210210", "", "Ciência da Operação"),
                                               @XmlEnumValue("210220")
                                               DESCONHECIMENTO_OPERACAO("210220", "", "Desconhecimento da Operação"),
                                               @XmlEnumValue("210240")
                                               OPERACAO_NAO_REALIZADA("210240", "", "Operação não Realizada"),
                                               @XmlEnumValue("110110")
                                               CCE("110110", "Carta de Correcao", "Carta de Correção Eletrônica"),
                                               @XmlEnumValue("110111")
                                               CANC_NFE("110111", "Cancelamento", "Cancelamento de NF-e");

    private static final long serialVersionUID = 1L;

    private String code;

    private String description;

    private String fullDescription;

    private EventType(String code, String description, String fullDescription) {
        this.code = code;
        this.description = description;
        this.fullDescription = fullDescription;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFullDescription() {
        return this.fullDescription;
    }

    public String getFullDescriptionWithNoAccents() {
        return StringUtils.stripAccents(this.fullDescription);
    }

    public static EventType findByCode(String code) {
        for (final EventType eventType : EventType.values()) {
            if (eventType.getCode().equals(code)) {
                return eventType;
            }
        }
        return null;
    }

    public static EventType findByFullDescriptionWithNoAccents(String eventDescription) {
        return Stream.of(EventType.values()).filter(et -> et.getFullDescriptionWithNoAccents().equals(eventDescription)).findFirst().orElse(null);
    }
}
