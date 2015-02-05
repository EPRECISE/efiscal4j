package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.types.CTeFormatCNPJ;
import eprecise.efiscal4j.cte.types.CTeStateRegistration;

/**
 * Tag - emit
 * 
 * @author Carlos Gomes
 * 
 */
public class Emitter implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "CNPJ") @CTeFormatCNPJ String cnpj;
    
    private final @XmlElement(name = "IE") @CTeStateRegistration String ie;
    
    private final @XmlElement(name = "xNome") @Size(min = 1, max = 60) String name;
    
    private final @XmlElement(name = "xFant") @Size(min = 1, max = 60) String fantasyName;
    
    private final @XmlElement(name = "enderEmit") Address address;
    
    public static class Builder {
	
	private String cnpj;
	
	private String ie;
	
	private String name;
	
	private String fantasyName;
	
	private Address address;
	
	public Builder withCNPJ(String cnpj) {
	    this.cnpj = cnpj;
	    return this;
	}
	
	public Builder withIE(String ie) {
	    this.ie = ie;
	    return this;
	}
	
	public Builder withName(String name) {
	    this.name = name;
	    return this;
	}
	
	public Builder withFantasyName(String fantasyName) {
	    this.fantasyName = fantasyName;
	    return this;
	}
	
	public Builder withAddress(Address address) {
	    this.address = address;
	    return this;
	}
	
    }
    
    public Emitter() {
	this.cnpj = null;
	this.ie = null;
	this.name = null;
	this.fantasyName = null;
	this.address = null;
    }
    
    public Emitter(Builder builder) {
	this.cnpj = builder.cnpj;
	this.ie = builder.ie;
	this.name = builder.name;
	this.fantasyName = builder.fantasyName;
	this.address = builder.address;
    }
    
    public String getCnpj() {
	return this.cnpj;
    }
    
    public String getIe() {
	return this.ie;
    }
    
    public String getName() {
	return this.name;
    }
    
    public String getFantasyName() {
	return this.fantasyName;
    }
    
    public Address getAddress() {
	return this.address;
    }
}
