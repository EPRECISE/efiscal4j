
package eprecise.efiscal4j.nfe.v400.tax.icms.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validador do BeanValidation que verifica se os dados padrões do grupo ICMS estão preenchidos
 * 
 * @author Felipe Bueno
 * 
 */
public class ICMS90Validator implements ConstraintValidator<ICMS90Validation, ICMS90Standard> {

	@Override
	public void initialize(ICMS90Validation constraintAnnotation) {
	}

	@Override
	public boolean isValid(ICMS90Standard icms90Standard, ConstraintValidatorContext context) {
		List<Object> icmsStandardGroup = new ArrayList<>();

		icmsStandardGroup.add(icms90Standard.getBcModality());
		icmsStandardGroup.add(icms90Standard.getBcValue());
		icmsStandardGroup.add(icms90Standard.getIcmsAliquot());
		icmsStandardGroup.add(icms90Standard.getIcmsValue());

		//@formatter:off
		if ((icmsStandardGroup.containsAll(Arrays.asList(new Object[] { null, null, null, null })) && icms90Standard.getBcReductionPercent() == null)) {
			return true;
		}

		ListIterator<Object> icmsStandardGroupIterator = icmsStandardGroup.listIterator();
				
		while (icmsStandardGroupIterator.hasNext()) {
			if (icmsStandardGroupIterator.next() == null) {
				return false;
			}
		}
		
		return true;
		//@formatter:on  
	}
}
