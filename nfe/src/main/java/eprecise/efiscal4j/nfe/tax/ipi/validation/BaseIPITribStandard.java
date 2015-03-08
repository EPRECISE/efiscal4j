
package eprecise.efiscal4j.nfe.tax.ipi.validation;

/**
 * Interface utilizada para agrupar dados padrões de IPI do grupo Trib. CST
 * 
 * @author Felipe Bueno
 * 
 */
@BaseIPITribValidation
public interface BaseIPITribStandard {

    public String getBcValue();

    public String getIpiAliquot();

    public String getUnityQuantity();

    public String getUnityValue();

    public String getIpiValue();

}
