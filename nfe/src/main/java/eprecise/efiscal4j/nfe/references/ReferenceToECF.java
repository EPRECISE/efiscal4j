
package eprecise.efiscal4j.nfe.references;

import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;


/**
 * Infromações da Cupom Fiscal vinculado à NF-e
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class ReferenceToECF implements DocumentReference {

    /**
     * @see ReferencedECFModel
     * @param model
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToEcf.model.isNotNull}") @Valid final ReferencedECFModel model;

    /**
     * Número de ordem seqüencial do ECF que emitiu o Cupom Fiscal vinculado à NF-e
     * 
     * @param ecfNumber
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToEcf.ecfNumber.isNotNull}") @Size(
            min = 1, max = 3, message = "{eprecise.efiscal4j.nfe.references.referenceToEcf.ecfNumber.isSize}") final String ecfNumber;

    /**
     * Número do Contador de Ordem de Operação - COO vinculado à NF-e
     * 
     * @param cooNumber
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.references.referenceToEcf.cooNumber.isNotNull}") @Size(
            min = 1, max = 6, message = "{eprecise.efiscal4j.nfe.references.referenceToEcf.cooNumber.isSize}") final String cooNumber;

    /**
     * Modelo do Documento Fiscal para Cupom Fiscal vinculado à NF-e
     * 
     */
    public enum ReferencedECFModel {
                                   NON_ECF("2B", "Cupom emitido por registradora (não ECF)"),
                                   ECF_PDV("2C", "Cupom fiscal PDV"),
                                   ECF("2D", "Cupom fiscal ECF");

        private final String value;

        private final String description;

        private ReferencedECFModel(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return this.value;
        }

        public String getDescription() {
            return this.description;
        }
        
        public static Optional<ReferencedECFModel> findBy(String value) {
            return Stream.of(ReferencedECFModel.values()).filter(e -> e.getValue().equals(value)).findFirst();
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

}
