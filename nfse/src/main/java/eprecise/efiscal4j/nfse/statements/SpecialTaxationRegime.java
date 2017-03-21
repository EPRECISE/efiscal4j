
package eprecise.efiscal4j.nfse.statements;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Regime especial de tributação
 *
 * @author Fernando C Glizt
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum SpecialTaxationRegime {

                                   @XmlEnumValue("1") MUNICIPAL_MICRO_ENTERPRISE(1, "Microempresa Municipal"),
                                   @XmlEnumValue("2") ESTIMATE(2, "Estimativa"),
                                   @XmlEnumValue("3") PROFESSIONAL_SOCIETY(3, "Sociedade de Profissionais"),
                                   @XmlEnumValue("4") COOPERATIVE(4, "Cooperativa"),
                                   @XmlEnumValue("5") MEI(5, "Microempresário Individual (MEI)"),
                                   @XmlEnumValue("6") ME_EPP(6, "Microempresário e Empresa de Pequeno Porte (ME EPP)"),
                                   @XmlEnumValue("7") SIMPLE_NATIONAL(7, "Simples Nacional");

    private final int value;

    private final String description;

    private SpecialTaxationRegime(final int value, final String description) {
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
