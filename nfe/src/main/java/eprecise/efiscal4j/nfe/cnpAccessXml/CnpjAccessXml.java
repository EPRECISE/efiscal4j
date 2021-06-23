package eprecise.efiscal4j.nfe.cnpAccessXml;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public class CnpjAccessXml implements CnpAccessXml{

    private @NotNull(message = "{eprecise.efiscal4j.nfe.cnpAccessXml.isNotNull}") @CNPJ(message = "{eprecise.efiscal4j.nfe.cnpAccessXml.cnpj.isCnpj}") final String cnpj;

    @Override
    public String getCnp() {
        return this.cnpj;
    }
}
