
package eprecise.efiscal4j.nfe.tax.icms;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Alíquota interestadual das UF envolvidas.
 * 
 * @author Felipe Bueno
 */
@XmlType
@XmlEnum(String.class)
public enum InterstateICMSUFAliquot implements Serializable {
    //@formatter:off
    @XmlEnumValue("4.00") PRODUTOS_IMPORTADOS("4.00", "Produtos Importados"),
    @XmlEnumValue("7.00") SUL_SUDESTE_EXCETO_ES("7.00", "Sul e Sudeste (exceto ES), destinado para os Estados do Norte e Nordeste ou ES"),
    @XmlEnumValue("12.00") DEMAIS_CASOS("12.00", "Demais casos");
    //@formatter:on
    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private InterstateICMSUFAliquot(String value, String description) {
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

    public static InterstateICMSUFAliquot findByCode(String code) {
        for (final InterstateICMSUFAliquot entity : InterstateICMSUFAliquot.values()) {
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
