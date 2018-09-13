
package eprecise.efiscal4j.nfse.ts.commons;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * Responsavel pela retencao do ISSQN: 1 - Tomador; 2 - Intermediário;
 *
 * @author Eduardo Costa Basso
 *
 */
@XmlType
@XmlEnum(Integer.class)
@RequiredArgsConstructor
public enum CommonsNFSeResponsibleRetention {
                                             @XmlEnumValue("1")
                                             TAKER(1, "Tomador"),
                                             @XmlEnumValue("2")
                                             INTERMEDIARY(2, "Intermediário");

    private @Getter final Integer value;

    private @Getter final String description;

    @Override
    public String toString() {
        return getDescription();
    }

    public static CommonsNFSeResponsibleRetention findByCode(final Integer value) {
        for (final CommonsNFSeResponsibleRetention entity : CommonsNFSeResponsibleRetention.values()) {
            if (entity.getValue().equals(value)) {
                return entity;
            }
        }
        return null;
    }

}
