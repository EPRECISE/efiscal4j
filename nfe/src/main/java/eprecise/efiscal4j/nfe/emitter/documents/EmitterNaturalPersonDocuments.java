
package eprecise.efiscal4j.nfe.emitter.documents;

import java.util.Optional;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.Builder;
import lombok.Getter;


/**
 * Documento de Pessoa Física do Emitente
 * 
 * @author Fernando Glizt
 * 
 */
@Getter
public class EmitterNaturalPersonDocuments extends EmitterDocuments {

    /**
     * CPF sem pontuação
     * 
     * @param cpf
     */
    private @CPF(message = "{eprecise.efiscal4j.nfe.emitter.documents.naturalPerson.cpf.isCpf}") final String cpf;

    @Builder
    public EmitterNaturalPersonDocuments(final String name, final String ie, final String ieSt, final String cpf, final EmitterMunicipalDocuments municipalDocuments) {
        super(name, ie, ieSt, municipalDocuments);
        this.cpf = Optional.ofNullable(cpf).orElse("");
    }

    @Override
    public String getCnp() {
        return this.cpf;
    }

}
