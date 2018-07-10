
package eprecise.efiscal4j.nfe.emitter.documents;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Documentos de Pessoa Física ou Jurídica do Emitente
 * 
 * @author Fernando Glizt
 * 
 */
@AllArgsConstructor
@Getter
public abstract class EmitterDocuments {
    
    public abstract String getCnp();

    /**
     * Nome ou Razão Social
     * 
     * @param name
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.documents.name.isNotNull}") @Size(
            min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.emitter.documents.name.isSize}") final String name;

    /**
     * Inscrição Estadual
     * 
     * @param ie
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.emitter.documents.ie.isNotNull}") @Pattern(
            regexp = "[0-9]{2,14}|ISENTO", message = "{eprecise.efiscal4j.nfe.emitter.documents.ie.isNotIe}") final String ie;

    /**
     * Inscrição Estadual do Substituto Tributário
     * 
     * @param ieSt
     */
    private @Pattern(regexp = "[0-9]{2,14}", message = "{eprecise.efiscal4j.nfe.emitter.documents.ieSt.isNotIeSt}") final String ieSt;

    /**
     * @see EmitterMunicipalDocuments
     * @param municipalDocuments
     */
    private @Valid final EmitterMunicipalDocuments municipalDocuments;

}
