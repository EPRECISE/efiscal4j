
package eprecise.efiscal4j.nfe.v400.tax.icms;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Origem da mercadoria:
 * 
 * @author Clécius J. Martinkoski
 * 
 */
@XmlType
@XmlEnum(Integer.class)
public enum ProductOrigin implements Serializable {
    @XmlEnumValue("0") NACIONAL("0", "Nacional"),
    @XmlEnumValue("1") ESTRANGEIRA_IMPORTADA("1", "Estrangeira - Importada"),
    @XmlEnumValue("2") ESTRANGEIRA_ADQUIRIDA_NACIONAL("2", "Estrangeira - Adquirida no mercado nacional"),
    @XmlEnumValue("3") NATIONAL_40_TO_70("3", "Nacional, conteudo superior 40% e inferior ou igual a 70%"),
    @XmlEnumValue("4") NATIONAL_BASIC_PRODUCTION_PROCESSES("4", "Nacional, processos produtivos básicos"),
    @XmlEnumValue("5") NATIONAL_LESS_40("5", "Nacional, conteudo inferior 40%"),
    @XmlEnumValue("6") FOREIGN_DIRECT_IMPORTS_WITH_SIMILAR_NATIONAL_COMEX_LIST("6", "Estrangeira - Importação direta, com similar nacional, lista CAMEX"),
    @XmlEnumValue("7") FOREIGN_INTERNAL_MARKET_WITHOUT_SIMILAR_NATIONAL_COMEX_LIST("7", "Estrangeira - mercado interno, sem similar,lista CAMEX"),
    @XmlEnumValue("8") NATIONAL_IMPORT_CONTENT_OVER_70("8", "Nacional, Conteúdo de Importação superior a 70%");

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
