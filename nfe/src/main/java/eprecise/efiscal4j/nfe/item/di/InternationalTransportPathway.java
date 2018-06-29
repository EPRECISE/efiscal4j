
package eprecise.efiscal4j.nfe.item.di;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * Via de transporte internacional
 * 
 * @author Fernando Glizt
 * 
 */
@XmlType
@XmlEnum(String.class)
public enum InternationalTransportPathway implements Serializable {
    //@formatter:off
	    MARITIME("1", "Marítima"),
	    RIVER("2", "Fluvial"),
	    LAKE("3", "Lacustre"),
	    AIR("4", "Aérea"),
	    POSTAL("5", "Postal"),
	    RAIL("6", "Ferroviária"),
	    ROAD("7", "Rodoviária"),
	    CONDUIT_TRANSMISSION_NETWORK("8", "Conduto / Rede transmissão"),
	    OWN_MEANS("9", "Meios próprios"),
	    FICTA_IN_OUT("10", "Entrada/Saída Ficta"),
	    COURIER("11", "Courier"),
	    HANDCARRY("12", "Handcarry");
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
