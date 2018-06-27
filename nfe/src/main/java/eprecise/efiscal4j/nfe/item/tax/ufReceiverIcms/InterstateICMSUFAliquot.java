
package eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms;

/**
 * Alíquota interestadual das UF envolvidas.
 * 
 * @author Fernando Glizt
 */
public enum InterstateICMSUFAliquot {
                                     IMPORTED_PRODUCTS("4.00", "Produtos Importados"),
                                     SOUTH_SOUTHEAST_EXCEPT_ES("7.00", "Sul e Sudeste (exceto ES), destinado para os Estados do Norte e Nordeste ou ES"),
                                     OTHER_CASES("12.00", "Demais casos");

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
