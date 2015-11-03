
package eprecise.efiscal4j.nfe.tax.icms.validation;

import eprecise.efiscal4j.nfe.tax.icms.BCModality;
import eprecise.efiscal4j.nfe.tax.icms.IcmsWithValue;


/**
 * Interface utilizada para agrupar dados padrões de ICMS para ICMS 90 e ICMSSN900
 *
 * @author Felipe Bueno
 *
 */
@ICMS90Validation
public interface ICMS90Standard extends IcmsWithValue {

    public BCModality getBcModality();

    public String getBcReductionPercent();

    public String getBcValue();

    public String getIcmsAliquot();
}
