
package eprecise.efiscal4j.nfe.transport;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import eprecise.efiscal4j.commons.domain.CFOP;
import eprecise.efiscal4j.commons.utils.DecimalPercent;
import eprecise.efiscal4j.nfe.transport.validations.TransportCFOP;
import lombok.Builder;
import lombok.Getter;


/**
 * Dados da retenção ICMS do Transporte
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class TransportICMSRetention {

    /**
     * Valor do Serviço
     * 
     * @param serviceValue
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.icmsRetention.serviceValue.isNotNull}") final BigDecimal serviceValue;

    /**
     * BC da Retenção do ICMS
     * 
     * @param retentionCalculationBasis
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.icmsRetention.retentionCalculationBasis.isNotNull}") final BigDecimal retentionCalculationBasis;

    /**
     * Alíquota da Retenção
     * 
     * @param retentionAliquot
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.icmsRetention.retentionAliquot.isNotNull}") final BigDecimal retentionAliquot;

    /**
     * Valor do ICMS Retido
     * 
     * @param retentionValue
     */
    private final BigDecimal retentionValue;

    /**
     * Código Fiscal de Operações e Prestações // PL_006f - alterado para permitir somente CFOP de transportes
     * 
     * @param cfop
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.icmsRetention.cfop.isNotNull}") @TransportCFOP(
            message = "{eprecise.efiscal4j.nfe.transport.icmsRetention.cfop.isNotTransport}") final CFOP cfop;

    /**
     * Código do Município de Ocorrência do Fato Gerador (utilizar a tabela do IBGE)
     * 
     * @param genFactIbgeCode
     */
    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.icmsRetention.genFactIbgeCode.isNotNull}") @Pattern(
            regexp = "[0-9]{7}", message = "{eprecise.efiscal4j.nfe.transport.icmsRetention.genFactIbgeCode.isNotIbgeCode}") final String genFactIbgeCode;

    public BigDecimal getRetentionValue() {
        return Optional.ofNullable(this.retentionValue).orElse(Optional.ofNullable(this.retentionCalculationBasis)
                .map(bc -> Optional.ofNullable(this.retentionAliquot).map(DecimalPercent::get).map(aliquot -> bc.multiply(aliquot)).orElse(null)).orElse(null));
    }

}
