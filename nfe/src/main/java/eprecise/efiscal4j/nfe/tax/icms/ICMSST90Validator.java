
package eprecise.efiscal4j.nfe.tax.icms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validador do BeanValidation que verifica se os dados padrões do grupo ICMS ST estão preenchidos
 * 
 * @author Felipe Bueno
 * 
 */
public class ICMSST90Validator implements ConstraintValidator<ICMSST90Validation, ICMSST90Standard> {

	@Override
	public void initialize(ICMSST90Validation constraintAnnotation) {
	}

	@Override
	public boolean isValid(ICMSST90Standard icmsSt90Standard, ConstraintValidatorContext context) {
		List<Object> icmsStStandardGroup = new ArrayList<>();

		icmsStStandardGroup.add(icmsSt90Standard.getBcModalitySt());
		icmsStStandardGroup.add(icmsSt90Standard.getBcValueST());
		icmsStStandardGroup.add(icmsSt90Standard.getIcmsStAliquot());
		icmsStStandardGroup.add(icmsSt90Standard.getIcmsStValue());

		//@formatter:off
		if ((icmsStStandardGroup.containsAll(Arrays.asList(new Object[] { null, null, null, null })) 
				&& icmsSt90Standard.getValueMarginAddedStPercent() == null 
				&& icmsSt90Standard.getBcReductionStPercent() == null)) {
			return true;
		}

		ListIterator<Object> icmsStandardGroupIterator = icmsStStandardGroup.listIterator();
				
		while (icmsStandardGroupIterator.hasNext()) {
			if (icmsStandardGroupIterator.next() == null) {
				return false;
			}
		}
		
		return true;
		//@formatter:on  
	}
}
