
package eprecise.efiscal4j.nfse.tc.govbr.v100.lot.rps;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Natureza da Operação ISS
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum GovbrNatureOperation {

                                  @XmlEnumValue("1")
                                  MUNICIPAL_TAXATION(1, "Tributação no município"),
                                  @XmlEnumValue("2")
                                  MUNICIPAL_OUTSIDE_TAXATION(2, "Tributação fora do município"),
                                  @XmlEnumValue("3")
                                  EXEMPTION(3, "Isenção"),
                                  @XmlEnumValue("4")
                                  IMMUNITY(4, "Imune"),
                                  @XmlEnumValue("5")
                                  SUSPENDED_BY_JUDICIAL_DECISION(5, "Suspensa por decisão judicial"),
                                  @XmlEnumValue("6")
                                  SUSPENDED_BY_ADMINISTRATIVE_PROCEDURE(6, "Suspensa por procedimento administrativo");

    private final Integer value;

    private final String description;

    private GovbrNatureOperation(final Integer value, final String description) {
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

    public static GovbrNatureOperation findByCode(final Integer natureOperation) {
        for (final GovbrNatureOperation entity : GovbrNatureOperation.values()) {
            if (entity.getValue().equals(natureOperation)) {
                return entity;
            }
        }
        return null;
    }

}
