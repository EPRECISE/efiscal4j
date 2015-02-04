package eprecise.efiscal4j.cte;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.address.UF;
import eprecise.efiscal4j.cte.types.CTeCFOP;
import eprecise.efiscal4j.cte.utils.ValidationBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class CTeIdentification {
    
    private final @XmlElement(name = "cUF") UF uf;
    
    private final @XmlElement(name = "cCT") @Pattern(regexp = "[0-9]{8}") String cTeCode;
    
    private final @XmlElement(name = "CFOP") @CTeCFOP String cfop;
    
    private final @XmlElement(name = "natOp") @Size(min = 1, max = 60) String operationNature;
    
    public static class Builder {
	
	private UF uf;
	
	private String cTeCode;
	
	private String cfop;
	
	public String operationNature;
	
	public Builder withUF(UF uf) {
	    this.uf = uf;
	    return this;
	}
	
	public Builder withCTeCode(String cTeCode) {
	    this.cTeCode = cTeCode;
	    return this;
	}
	
	public Builder withCFOP(String cfop) {
	    this.cfop = cfop;
	    return this;
	}
	
	public Builder withOperationNature(String operationNature) {
	    this.operationNature = operationNature;
	    return this;
	}
	
	public CTeIdentification build() {
	    final CTeIdentification entity = new CTeIdentification(this);
	    ValidationBuilder.from(entity).validate().throwIfViolate();
	    return entity;
	}
    }
    
    public CTeIdentification() {
	this.uf = null;
	this.cTeCode = null;
	this.cfop = null;
	this.operationNature = null;
    }
    
    public CTeIdentification(Builder builder) {
	this.uf = builder.uf;
	this.cTeCode = builder.cTeCode;
	this.cfop = builder.cfop;
	this.operationNature = builder.operationNature;
	
    }
    
    public UF getUf() {
	return this.uf;
    }
    
    public String getCTeCode() {
	return this.cTeCode;
    }
    
    public String getCfop() {
	return this.cfop;
    }
    
    public String getOperationNature() {
	return this.operationNature;
    }
    
}
