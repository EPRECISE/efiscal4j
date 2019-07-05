
package eprecise.efiscal4j.nfe.item.tax.icms;

/**
 * Origem da mercadoria:
 *
 * @author Fernando Glizt
 *
 */
public enum ProductOrigin {
                           NATIONAL("0", "Nacional"),
                           FOREIGN_ORIGIN("1", "Estrangeira - Importada"),
                           FOREIGN_ORIGIN_NACIONAL("2", "Estrangeira - Adquirida no mercado nacional"),
                           NATIONAL_40_TO_70("3", "Nacional, conteudo superior 40% e inferior ou igual a 70%"),
                           NATIONAL_BASIC_PRODUCTION_PROCESSES("4", "Nacional, processos produtivos básicos"),
                           NATIONAL_LESS_40("5", "Nacional, conteudo inferior 40%"),
                           FOREIGN_DIRECT_IMPORTS_WITH_SIMILAR_NATIONAL_COMEX_LIST("6", "Estrangeira - Importação direta, com similar nacional, lista CAMEX"),
                           FOREIGN_INTERNAL_MARKET_WITHOUT_SIMILAR_NATIONAL_COMEX_LIST("7", "Estrangeira - mercado interno, sem similar,lista CAMEX"),
                           NATIONAL_IMPORT_CONTENT_OVER_70("8", "Nacional, Conteúdo de Importação superior a 70%");

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private ProductOrigin(final String value, final String description) {
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

    public static ProductOrigin findByCode(final String code) {
        for (final ProductOrigin productOrigin : values()) {
            if (productOrigin.getValue().equals(code)) {
                return productOrigin;
            }
        }
        return null;
    }
}
