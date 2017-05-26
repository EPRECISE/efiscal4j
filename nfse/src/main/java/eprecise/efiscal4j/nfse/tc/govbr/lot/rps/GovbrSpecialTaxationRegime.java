
package eprecise.efiscal4j.nfse.tc.govbr.lot.rps;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.nfse.domain.specialTaxationRegime.NFSeSpecialTaxationRegime;


/**
 * Regime especial de tributação
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum GovbrSpecialTaxationRegime implements NFSeSpecialTaxationRegime{

                                   @XmlEnumValue("1") MUNICIPAL_MICRO_ENTERPRISE(1, "Microempresa Municipal"),
                                   @XmlEnumValue("2") ESTIMATE(2, "Estimativa"),
                                   @XmlEnumValue("3") PROFESSIONAL_SOCIETY(3, "Sociedade de Profissionais"),
                                   @XmlEnumValue("4") COOPERATIVE(4, "Cooperativa");

    private final int value;

    private final String description;

    private GovbrSpecialTaxationRegime(final int value, final String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
