
package eprecise.efiscal4j.nfe.emitter.documents;

import java.util.Optional;

import javax.validation.constraints.Size;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.Builder;
import lombok.Getter;


/**
 * Documento de Pessoa Jurídica do Emitente
 * 
 * @author Fernando Glizt
 * 
 */
@Getter
public class EmitterLegalEntityDocuments extends EmitterDocuments {

    /**
     * CNPJ sem pontuação
     * 
     * @param cnpj
     */
    private @CNPJ(message = "{eprecise.efiscal4j.nfe.emitter.documents.legalEntity.cnpj.isCnpj}") final String cnpj;

    /**
     * Nome Fantasia
     * 
     * @param fancyName
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.emitter.documents.legalEntity.fancyName.isSize}") final String fancyName;

    @Builder
    public EmitterLegalEntityDocuments(final String name, final String ie, final String ieSt, final String cnpj, final String fancyName, final EmitterMunicipalDocuments municipalDocuments) {
        super(name, ie, ieSt, municipalDocuments);
        this.cnpj = Optional.ofNullable(cnpj).orElse("");
        this.fancyName = fancyName;
    }

    @Override
    public String getCnp() {
        return this.cnpj;
    }

}
