
package eprecise.efiscal4j.nfe.item.di;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

/**
 * Adições (NT 2011/004)
 *
 * @author Fernando Glizt
 *
 */

@Builder
@Getter
public class Addition {

	private final Integer number;

	private final Integer sequence;

	private final String manufacturerCode;

	private final BigDecimal discountValue;

	private final String drawbackNumber;

}
