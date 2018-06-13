
package eprecise.efiscal4j.nfe.references;

import java.time.YearMonth;

import javax.validation.constraints.NotNull;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.domain.adress.UF;
import lombok.Builder;
import lombok.Getter;


/**
 * Infromações da NF modelo 1/1A referenciada ou NF modelo 2 referenciada
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class ReferenceToNF implements DocumentReference {

    /**
     * Série do Documento Fiscal
     * 
     * @param series
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNf.series.isNotNull}") final String series;

    /**
     * Número do Documento Fiscal
     * 
     * @param number
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNf.number.isNotNull}") final Long number;

    /**
     * UF do emitente do Documento Fiscal
     * 
     * @param uf
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNf.uf.isNotNull}") final UF uf;

    /**
     * CNPJ do emitente do documento fiscal
     * 
     * @param cnpj
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNf.cnpj.isNotNull}") @CNPJ(
            message = "{eprecise.efiscal4j.nfe.references.referenceToNf.cnpj.isNotCnpj}") final String cnpj;

    /**
     * Ano e mês da emissão
     * 
     * @param month
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNf.month.isNotNull}") final YearMonth month;

}
