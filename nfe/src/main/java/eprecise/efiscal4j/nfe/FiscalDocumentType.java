
package eprecise.efiscal4j.nfe;

/**
 * Tipo do Documento Fiscal (0 - entrada; 1 - saída)
 * 
 * @author Fernando Glizt
 * 
 */
public enum FiscalDocumentType {
                                IN("0", "Entrada"),
                                OUT("1", "Saida");

    private static final long serialVersionUID = 1L;

    private String type;

    private String description;

    private FiscalDocumentType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public static FiscalDocumentType findByCode(final String code) {
        for (final FiscalDocumentType entity : FiscalDocumentType.values()) {
            if (entity.getType().equals(code)) {
                return entity;
            }
        }
        return null;
    }

}
