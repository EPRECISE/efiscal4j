
package eprecise.efiscal4j.nfe.item.tax.icms;

/**
 * Origem da mercadoria:
 * 
 * 0 - Nacional;
 * 
 * 1 - Estrangeira - Importação direta;
 * 
 * 2 - Estrangeira - Adquirida no mercado interno;
 * 
 * @author Fernando Glizt
 * 
 */
public enum ProductOrigin {
                           NATIONAL("0", "Nacional"),
                           FOREIGN_ORIGIN("1", "Estrangeira - Importada"),
                           FOREIGN_ORIGIN_NACIONAL("2", "Estrangeira - Adquirida no mercado nacional");

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
