
package eprecise.efiscal4j.nfe.v400.tax.cofins.validation;

/**
 * Interface utilizada para agrupar dados padrões de COFINS do grupo outros CST
 * 
 * @author Felipe Bueno
 * 
 */
@BaseCOFINSOtherValidation
public interface BaseCOFINSOtherStandard {

    public String getBcValue();

    public String getCofinsAliquot();

    public String getProductQuantity();

    public String getProductAliquot();

    public String getCofinsValue();

}
