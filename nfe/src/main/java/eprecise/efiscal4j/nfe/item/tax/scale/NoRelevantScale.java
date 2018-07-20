
package eprecise.efiscal4j.nfe.item.tax.scale;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class NoRelevantScale implements ScaleTaxIndicator {

    private final @CNPJ(formatted = false) String manufacturerCnpj;

}
