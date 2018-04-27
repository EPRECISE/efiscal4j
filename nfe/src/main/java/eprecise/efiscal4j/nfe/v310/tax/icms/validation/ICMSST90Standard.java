
package eprecise.efiscal4j.nfe.v310.tax.icms.validation;

import eprecise.efiscal4j.nfe.v310.tax.icms.BCModalityST;
import eprecise.efiscal4j.nfe.v310.tax.icms.IcmsWithST;


/**
 * Interface utilizada para agrupar dados padrões de ICMS ST para ICMS 90 e ICMSSN900
 *
 * @author Felipe Bueno
 *
 */
@ICMSST90Validation
public interface ICMSST90Standard extends IcmsWithST {

    public BCModalityST getBcModalitySt();

    public String getValueMarginAddedStPercent();

    public String getBcReductionStPercent();

    public String getBcValueST();

    public String getIcmsStAliquot();
}
