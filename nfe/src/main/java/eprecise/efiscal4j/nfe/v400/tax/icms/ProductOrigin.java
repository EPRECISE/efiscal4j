
package eprecise.efiscal4j.nfe.v400.tax.icms;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Origem da mercadoria:
 * 
 * 0 - Nacional;
 * 
 * 1 - Estrangeira - Importação direta;
 * 
 * 2 - Estrangeira - Adquirida no mercado interno;
 * 
 * @author Clécius J. Martinkoski
 * 
 */
@XmlType
@XmlEnum(Integer.class)
public enum ProductOrigin implements Serializable {
    @XmlEnumValue("0") NACIONAL("0", "Nacional"),
    @XmlEnumValue("1") ESTRANGEIRA_IMPORTADA("1", "Estrangeira - Importada"),
    @XmlEnumValue("2") ESTRANGEIRA_ADQUIRIDA_NACIONAL("2", "Estrangeira - Adquirida no mercado nacional");

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private ProductOrigin(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getProductOriginWithDescription() {
        return this.value + " - " + this.description;
    }

    public static ProductOrigin findByCode(String code) {
        for (final ProductOrigin productOrigin : values()) {
            if (productOrigin.getValue().equals(code)) {
                return productOrigin;
            }
        }
        return null;
    }
}
