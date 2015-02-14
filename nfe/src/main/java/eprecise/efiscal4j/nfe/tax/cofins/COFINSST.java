package eprecise.efiscal4j.nfe.tax.cofins;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.tax.cofins.validation.BaseCOFINSOtherStandard;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal1104;
import eprecise.efiscal4j.nfe.types.NFeDecimal1204;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302Optional;

/**
 * Dados do COFINS Substituição Tributária
 * 
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class COFINSST implements Serializable, BaseCOFINSOtherStandard {
    
    private static final long serialVersionUID = 1L;
    
    private @XmlElement(name = "vBC") @NFeDecimal1302Optional final String bcValue;
    
    private @XmlElement(name = "pCOFINS") @NFeDecimal0302a04 final String cofinsAliquot;
    
    private @XmlElement(name = "qBCProd") @NFeDecimal1204 final String productQuantity;
    
    private @XmlElement(name = "vAliqProd") @NFeDecimal1104 final String productAliquot;
    
    private @XmlElement(name = "vCOFINS") @NotNull @NFeDecimal1302 final String cofinsValue;
    
    public static class Builder {
	
	private String bcValue;
	
	private String cofinsAliquot;
	
	private String productQuantity;
	
	private String productAliquot;
	
	private String cofinsValue;
	
	/**
	 * Valor da BC do COFINS ST
	 * 
	 * @param bcValue
	 * @return
	 */
	public Builder withBcValue(String bcValue) {
	    this.bcValue = bcValue;
	    return this;
	}
	
	/**
	 * Alíquota do COFINS ST (em percentual)
	 * 
	 * @param cofinsAliquot
	 * @return
	 */
	public Builder withCofinsAliquot(String cofinsAliquot) {
	    this.cofinsAliquot = cofinsAliquot;
	    return this;
	}
	
	/**
	 * Quantidade Vendida
	 * 
	 * @param productQuantity
	 * @return
	 */
	public Builder withProductQuantity(String productQuantity) {
	    this.productQuantity = productQuantity;
	    return this;
	}
	
	/**
	 * Alíquota do COFINS ST (em reais)
	 * 
	 * @param productAliquot
	 * @return
	 */
	public Builder withProductAliquot(String productAliquot) {
	    this.productAliquot = productAliquot;
	    return this;
	}
	
	/**
	 * Valor do COFINS ST
	 * 
	 * @param cofinsValue
	 * @return
	 */
	public Builder withCofinsValue(String cofinsValue) {
	    this.cofinsValue = cofinsValue;
	    return this;
	}
	
	public COFINSST build() {
	    final COFINSST entity = new COFINSST(this);
	    ValidationBuilder.from(entity).validate().throwIfViolate();
	    return entity;
	}
    }
    
    public COFINSST(Builder builder) {
	this.bcValue = builder.bcValue;
	this.cofinsAliquot = builder.cofinsAliquot;
	this.productQuantity = builder.productQuantity;
	this.productAliquot = builder.productAliquot;
	this.cofinsValue = builder.cofinsValue;
    }
    
    @Override
    public String getBcValue() {
	return this.bcValue;
    }
    
    @Override
    public String getCofinsAliquot() {
	return this.cofinsAliquot;
    }
    
    @Override
    public String getProductQuantity() {
	return this.productQuantity;
    }
    
    @Override
    public String getProductAliquot() {
	return this.productAliquot;
    }
    
    @Override
    public String getCofinsValue() {
	return this.cofinsValue;
    }
    
}
