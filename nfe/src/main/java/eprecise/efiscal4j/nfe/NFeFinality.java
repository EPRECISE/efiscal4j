
package eprecise.efiscal4j.nfe;

/**
 * Finalidade da NF-e (1=Normal; 2=Complementar; 3=Ajuste; 4=Devolução/Retorno)
 * 
 * @author Fernando Glizt
 * 
 */
public enum NFeFinality {

                         NORMAL(1, "Normal"),
                         COMPLEMENTARY(2, "Complementar"),
                         ADJUSTMENT(3, "Ajuste"),
                         RETURN(4, "Devolução/Retorno");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private NFeFinality(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}
