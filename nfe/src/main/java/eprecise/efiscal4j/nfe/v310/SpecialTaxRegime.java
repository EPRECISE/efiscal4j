
package eprecise.efiscal4j.nfe.v310;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Código do regime especial de tributação
 * 
 */
@XmlType
@XmlEnum(Integer.class)
public enum SpecialTaxRegime {

    @XmlEnumValue("1") MICROEMPRESA_MUNICIPAL(1, "Microempresa Municipal"),
    @XmlEnumValue("2") ESTIMATIVA(2, "Estimativa"),
    @XmlEnumValue("3") SOCIEDADE_PROFISSIONAIS(3, "Sociedade de Profissionais"),
    @XmlEnumValue("4") COOPERATIVA(4, "Cooperativa"),
    @XmlEnumValue("5") MEI(5, "Microempresário Individual (MEI)"),
    @XmlEnumValue("6") ME_EPP(6, "Microempresário e Empresa de Pequeno Porte (ME/EPP)");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private SpecialTaxRegime(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
