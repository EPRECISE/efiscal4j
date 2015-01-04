
package eprecise.efiscal4j.nfe.tax.icms;

/**
 * Interface utilizada para agrupar dados padrões de ICMS ST para ICMS 90 e ICMSSN900
 * 
 * @author Felipe Bueno
 * 
 */
@ICMSST90Validation
public interface ICMSST90Standard {

	public BCModalityST getBcModalitySt();

	public String getValueMarginAddedStPercent();

	public String getBcReductionStPercent();

	public String getBcValueST();

	public String getIcmsStAliquot();

	public String getIcmsStValue();
}
