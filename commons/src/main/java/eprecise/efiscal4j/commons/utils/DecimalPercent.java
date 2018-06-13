
package eprecise.efiscal4j.commons.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class DecimalPercent {

    private static final MathContext MATH_CONTEXT = new MathContext(2, RoundingMode.HALF_UP);

    public static BigDecimal get(final BigDecimal value) {
        return value.divide(BigDecimal.valueOf(100), MATH_CONTEXT);
    }

    public static BigDecimal from(final BigDecimal value) {
        return value.multiply(new BigDecimal(100));
    }

}
