
package eprecise.efiscal4j.nfe.v310.item.di;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Via de transporte internacional
 * 
 * @author Felipe Bueno
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum InternationalTransportPathway implements Serializable {
    //@formatter:off
    @XmlEnumValue("1") MARITIMA("1", "Marítima"),
    @XmlEnumValue("2") FLUVIAL("2", "Fluvial"),
    @XmlEnumValue("3") LACUSTRE("3", "Lacustre"),
    @XmlEnumValue("4") AEREA("4", "Aérea"),
    @XmlEnumValue("5") POSTAL("5", "Postal"),
    @XmlEnumValue("6") FERROVIARIA("6", "Ferroviária"),
    @XmlEnumValue("7") RODOVIARIA("7", "Rodoviária"),
    @XmlEnumValue("8") CONDUTO_REDE_TRANSMISSAO("8", "Conduto / Rede transmissão"),
    @XmlEnumValue("9") MEIOS_PROPRIOS("9", "Meios próprios"),
    @XmlEnumValue("10") ENTRADA_SAIDA_FICTA("10", "Entrada/Saída Ficta"),
    @XmlEnumValue("11") COURIER("11", "Courier"),
    @XmlEnumValue("12") HANDCARRY("12", "Handcarry");
    //@formatter:on
    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private InternationalTransportPathway(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getValueWithDescription() {
        return this.value + " - " + this.description;
    }

    public static InternationalTransportPathway findByCode(String code) {
        for (final InternationalTransportPathway entity : InternationalTransportPathway.values()) {
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
