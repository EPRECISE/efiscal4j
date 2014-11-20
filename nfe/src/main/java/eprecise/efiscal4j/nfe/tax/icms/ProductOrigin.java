
package eprecise.efiscal4j.nfe.tax.icms;

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
public enum ProductOrigin {
    @XmlEnumValue("0") NACIONAL("Nacional"),
    @XmlEnumValue("1") ESTRANGEIRA_IMPORTADA("Estrangeira - Importada"),
    @XmlEnumValue("2") ESTRANGEIRA_ADQUIRIDA_NACIONAL("Estrangeira - Adquirida no mercado nacional");

    private final String description;

    private ProductOrigin(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
