package eprecise.efiscal4j.nfe.v400.tax.pis.validation;

/**
 * Interface utilizada para agrupar dados padrões de PIS do grupo outros CST
 * 
 * @author Felipe Bueno
 * 
 */
@BasePISOtherValidation
public interface BasePISOtherStandard {

    public String getBcValue();

    public String getPisAliquot();
    
    public String getProductQuantity();

    public String getProductAliquot();

    public String getPisValue();
    
}
