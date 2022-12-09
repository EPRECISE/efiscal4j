
package eprecise.efiscal4j.nfse.tc.goiania.lot.statements;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.nfse.domain.specialTaxationRegime.NFSeSpecialTaxationRegime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@XmlType
@XmlEnum(Integer.class)
@RequiredArgsConstructor
public enum GoianiaSpecialTaxationRegime implements NFSeSpecialTaxationRegime {

                                                                             @XmlEnumValue("1")
                                                                             MUNICIPAL_MICRO_ENTERPRISE(1, "Microempresa Municipal"),
                                                                             @XmlEnumValue("2")
                                                                             ESTIMATE(2, "Estimativa"),
                                                                             @XmlEnumValue("3")
                                                                             PROFESSIONAL_SOCIETY(3, "Sociedade de Profissionais"),
                                                                             @XmlEnumValue("4")
                                                                             COOPERATIVE(4, "Cooperativa"),
                                                                             @XmlEnumValue("5")
                                                                             MEI(5, "Microempresário Individual (MEI)"),
                                                                             @XmlEnumValue("6")
                                                                             ME_EPP(6,
                                                                                     "Microempresário e Empresa de Pequeno Porte (ME EPP)");

    private @Getter final Integer value;

    private @Getter final String description;

    @Override
    public String toString() {
        return getDescription();
    }

    public static GoianiaSpecialTaxationRegime findByValue(final Integer value) {
        for (final GoianiaSpecialTaxationRegime entity : GoianiaSpecialTaxationRegime.values()) {
            if (entity.getValue().equals(value)) {
                return entity;
            }
        }
        return null;
    }
}
