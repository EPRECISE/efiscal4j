
package eprecise.efiscal4j.nfe.transport.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import eprecise.efiscal4j.commons.domain.CFOP;


/**
 * Valida se o CFOP informado é de transporte
 * 
 * @author Fernando Glizt
 * 
 */
public class TransportCFOPValidator implements ConstraintValidator<TransportCFOP, CFOP> {

    @Override
    public void initialize(TransportCFOP constraintAnnotation) {
    }

    @Override
    public boolean isValid(CFOP cfop, ConstraintValidatorContext context) {
        return cfop == null || (cfop != null && cfop.isTransport());
    }
}
