
package eprecise.efiscal4j.nfe.references;

import java.time.YearMonth;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.caelum.stella.bean.validation.CPF;
import eprecise.efiscal4j.commons.domain.adress.UF;
import lombok.Builder;
import lombok.Getter;


/**
 * Informações NF de produtor referenciada
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class ReferenceToNFP implements DocumentReference {

    /**
     * Série do Documento Fiscal
     * 
     * @param series
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.series.isNotNull}") final String series;

    /**
     * Número do Documento Fiscal
     * 
     * @param number
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.number.isNotNull}") final Long number;

    /**
     * UF do emitente do Documento Fiscal
     * 
     * @param uf
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.uf.isNotNull}") final UF uf;

    /**
     * @see NfpCnp
     * @param month
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.cnp.isNotNull}") @Valid final NfpCnp cnp;

    /**
     * Ano e mês da emissão
     * 
     * @param month
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.month.isNotNull}") final YearMonth month;

    /**
     * IE do emitente da NF de Produtor
     * 
     * @param ie
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.ie.isNotNull}") @Pattern(
            regexp = "ISENTO|[0-9]{2,14}", message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.ie.isNotIe}") final String ie;

    /**
     * @see ProducerReferencedNFModel
     * @param model
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.model.isNotNull}") @Valid final ProducerReferencedNFModel model;

    /**
     * Modelo do Documento Fiscal da NF de produtor referenciada
     * 
     */
    public enum ProducerReferencedNFModel {
                                           PRODUCER_NF("04", "NF de produtor"),
                                           SPARE_NF("01", "NF Avulsa");

        private final String value;

        private final String description;

        private ProducerReferencedNFModel(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return this.value;
        }

        public String getDescription() {
            return this.description;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
        
        public static Optional<ProducerReferencedNFModel> findBy(String value) {
            return Stream.of(ProducerReferencedNFModel.values()).filter(e -> e.getValue().equals(value)).findFirst();
        }
    }

    /**
     * CPF ou CNPJ da NF de produtor
     *
     */
    public interface NfpCnp {

        String getCnp();
    }

    @Builder
    public static class NfpCnpj implements NfpCnp {

        /**
         * CNPJ sem pontuação
         * 
         * @param cnpj
         */
        private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.cnp.isNotNull}") @CNPJ(
                message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.cnp.cnpj.isCnpj}") final String cnpj;

        @Override
        public String getCnp() {
            return this.cnpj;
        }
    }

    @Builder
    public static class NfpCpf implements NfpCnp {

        /**
         * CPF sem pontuação
         * 
         * @param cpf
         */
        private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.cnp.isNotNull}") @CPF(
                message = "{eprecise.efiscal4j.nfe.references.referenceToNfp.cnp.cpf.isCpf}") final String cpf;

        @Override
        public String getCnp() {
            return this.cpf;
        }
    }

}
