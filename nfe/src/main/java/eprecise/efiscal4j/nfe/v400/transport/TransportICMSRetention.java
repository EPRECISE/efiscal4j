package eprecise.efiscal4j.nfe.v400.transport;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.CFOP;
import eprecise.efiscal4j.nfe.v400.transport.validation.TransportCFOPValidation;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;

/**
 * Dados da retenção ICMS do Transporte
 * 
 * @author Felipe Bueno
 * 
 */
@TransportCFOPValidation
@XmlAccessorType(XmlAccessType.FIELD)
public class TransportICMSRetention implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private @XmlElement(name = "vServ") @NotNull @NFeDecimal1302 final String serviceValue;
    
    private @XmlElement(name = "vBCRet") @NotNull @NFeDecimal1302 final String retentionCalculationBasis;
    
    private @XmlElement(name = "pICMSRet") @NotNull @NFeDecimal0302a04 final String retentionAliquot;
    
    private @XmlElement(name = "vICMSRet") @NotNull @NFeDecimal1302 final String retentionValue;
    
    private @XmlElement(name = "CFOP") @NotNull final CFOP cfop;
    
    private @XmlElement(name = "cMunFG") @NotNull @Pattern(regexp = "[0-9]{7}") final String genFactIbgeCode;
    
    public static class Builder {
	
	private String serviceValue;
	
	private String retentionCalculationBasis;
	
	private String retentionAliquot;
	
	private String retentionValue;
	
	private CFOP cfop;
	
	private String genFactIbgeCode;
	
	/**
	 * Valor do Serviço
	 * 
	 * @param serviceValue
	 * @return
	 */
	public Builder withServiceValue(String serviceValue) {
	    this.serviceValue = serviceValue;
	    return this;
	}
	
	/**
	 * BC da Retenção do ICMS
	 * 
	 * @param retentionCalculationBasis
	 * @return
	 */
	public Builder withRetentionCalculationBasis(String retentionCalculationBasis) {
	    this.retentionCalculationBasis = retentionCalculationBasis;
	    return this;
	}
	
	/**
	 * Alíquota da Retenção
	 * 
	 * @param retentionAliquot
	 * @return
	 */
	public Builder withRetentionAliquot(String retentionAliquot) {
	    this.retentionAliquot = retentionAliquot;
	    return this;
	}
	
	/**
	 * Valor do ICMS Retido
	 * 
	 * @param retentionValue
	 * @return
	 */
	public Builder withRetentionValue(String retentionValue) {
	    this.retentionValue = retentionValue;
	    return this;
	}
	
	/**
	 * Código Fiscal de Operações e Prestações // PL_006f - alterado para
	 * permitir somente CFOP de transportes
	 * 
	 * @param cfop
	 * @return
	 */
	public Builder withCfop(CFOP cfop) {
	    this.cfop = cfop;
	    return this;
	}
	
	/**
	 * Código do Município de Ocorrência do Fato Gerador (utilizar a tabela
	 * do IBGE)
	 * 
	 * @param genFactIbgeCode
	 * @return
	 */
	public Builder withGenFactIbgeCode(String genFactIbgeCode) {
	    this.genFactIbgeCode = genFactIbgeCode;
	    return this;
	}
	
	public TransportICMSRetention build() {
	    final TransportICMSRetention entity = new TransportICMSRetention(this);
	    ValidationBuilder.from(entity).validate().throwIfViolate();
	    return entity;
	}
	
    }
    
    public TransportICMSRetention() {
	this.serviceValue = null;
	this.retentionCalculationBasis = null;
	this.retentionAliquot = null;
	this.retentionValue = null;
	this.cfop = null;
	this.genFactIbgeCode = null;
    }
    
    public TransportICMSRetention(Builder builder) {
	this.serviceValue = builder.serviceValue;
	this.retentionCalculationBasis = builder.retentionCalculationBasis;
	this.retentionAliquot = builder.retentionAliquot;
	this.retentionValue = builder.retentionValue;
	this.cfop = builder.cfop;
	this.genFactIbgeCode = builder.genFactIbgeCode;
	
    }
    
    public String getServiceValue() {
	return this.serviceValue;
    }
    
    public String getRetentionCalculationBasis() {
	return this.retentionCalculationBasis;
    }
    
    public String getRetentionAliquot() {
	return this.retentionAliquot;
    }
    
    public String getRetentionValue() {
	return this.retentionValue;
    }
    
    public CFOP getCfop() {
	return this.cfop;
    }
    
    public String getGenFactIbgeCode() {
	return this.genFactIbgeCode;
    }
    
}
