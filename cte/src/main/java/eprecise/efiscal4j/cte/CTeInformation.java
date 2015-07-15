package eprecise.efiscal4j.cte;

import java.io.Serializable;

/**
 * Tipo de informação do CTe podendo ser Normal, Complementar e de Anulação
 * 
 * @author Clecius J. Martinkoski
 *
 */
public abstract class CTeInformation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * Grupo de informações do CT-e Normal e Substituto
     */
    public static final Class<CTeRegularInformation.Builder> REGULAR = CTeRegularInformation.Builder.class;
    /**
     * Detalhamento do CT-e complementado
     */
    public static final Class<CTeComplementaryInformation.Builder> COMPLEMENTARY = CTeComplementaryInformation.Builder.class;
    /**
     * Detalhamento do CT-e do tipo Anulação
     */
    public static final Class<CTeAnnulmentInformation.Builder> ANNULMENT = CTeAnnulmentInformation.Builder.class;
    
    public static class Builder {
	
	public <T extends Builder> T fromType(Class<T> type) {
	    try {
		return type.newInstance();
	    } catch (InstantiationException | IllegalAccessException e) {
		throw new RuntimeException(e);
	    }
	}
    }
    
}
