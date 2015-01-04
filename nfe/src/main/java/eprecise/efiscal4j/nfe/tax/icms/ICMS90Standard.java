
package eprecise.efiscal4j.nfe.tax.icms;

/**
 * Interface utilizada para agrupar dados padrões de ICMS para ICMS 90 e ICMSSN900
 * 
 * @author Felipe Bueno
 * 
 */
@ICMS90Validation
public interface ICMS90Standard {

	public BCModality getBcModality();

	public String getBcReductionPercent();

	public String getBcValue();

	public String getIcmsAliquot();

	public String getIcmsValue();

}
