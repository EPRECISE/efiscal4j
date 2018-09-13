
package eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * Exigibilidade do ISS da NFS-e
 *
 * @author Eduardo Costa Basso
 *
 */
@XmlType
@XmlEnum(Integer.class)
@RequiredArgsConstructor
public enum GovbrIssRequirement {
                                 @XmlEnumValue("1")
                                 REQUIRED(1, "Exigível"),
                                 @XmlEnumValue("2")
                                 NO_INCIDENCE(2, "Não incidência"),
                                 @XmlEnumValue("3")
                                 EXEMPTION(3, "Isenção"),
                                 @XmlEnumValue("4")
                                 EXPORTATION(4, "Exportação"),
                                 @XmlEnumValue("5")
                                 IMMUNITY(5, "Imunidade"),
                                 @XmlEnumValue("6")
                                 SUSPENDED_BY_JUDICIAL_DECISION(6, "Suspensa por decisão judicial"),
                                 @XmlEnumValue("7")
                                 SUSPENDED_BY_ADMINISTRATIVE_PROCEEDINGS(7, "Suspensa por processo adiministrativo");

    private @Getter final Integer value;

    private @Getter final String description;

    @Override
    public String toString() {
        return getDescription();
    }

}
