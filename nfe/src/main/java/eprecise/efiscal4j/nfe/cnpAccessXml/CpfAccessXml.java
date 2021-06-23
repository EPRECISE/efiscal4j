package eprecise.efiscal4j.nfe.cnpAccessXml;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public class CpfAccessXml implements CnpAccessXml{

    private @NotNull(message = "{eprecise.efiscal4j.nfe.cnpAccessXml.isNotNull}") @CPF(message = "{eprecise.efiscal4j.nfe.cnpAccessXml.cpf.isCpf}") final String cpf;

    @Override
    public String getCnp() {
        return this.cpf;
    }
}
