package eprecise.efiscal4j.cte.serviceTaker;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.types.FormatCNPJ;
import eprecise.efiscal4j.cte.types.FormatCPF;
import eprecise.efiscal4j.cte.types.StateRegistration;
import eprecise.efiscal4j.cte.types.TypeEmail;
import eprecise.efiscal4j.cte.types.TypeFone;

@XmlAccessorType(XmlAccessType.FIELD)
public class OthersServiceTaker extends ServiceTaker {
    
    private static final long serialVersionUID = 1L;
    
    private static final String VALUE = "4";
    
    private final @XmlElement(name = "CNPJ") @FormatCNPJ String cnpj;
    
    private final @XmlElement(name = "CPF") @FormatCPF String cpf;
    
    private final @XmlElement(name = "IE") @StateRegistration String ie;
    
    private final @XmlElement(name = "xNome") @Size(min = 1, max = 60) String corporateName;
    
    private final @XmlElement(name = "xFant") @Size(min = 1, max = 60) String fantasyName;
    
    private final @XmlElement(name = "email") @TypeEmail @Size(min = 1, max = 60) String email;
    
    private final @XmlElement(name = "enderToma") Address address;
    
    private final @XmlElement(name = "fone") @TypeFone String fone;
    
    public static class Builder extends ServiceTaker.Builder {
	
	private String cnpj;
	
	private String cpf;
	
	private String ie;
	
	private String corporateName;
	
	private String fantasyName;
	
	private String fone;
	
	private String email;
	
	private Address address;
	
	public Builder withCNPJ(String CNPJ) {
	    this.cnpj = CNPJ;
	    return this;
	}
	
	public Builder withCPF(String cpf) {
	    this.cpf = cpf;
	    return this;
	}
	
	public Builder withIE(String ie) {
	    this.ie = ie;
	    return this;
	}
	
	public Builder withCorporateName(String corporateName) {
	    this.corporateName = corporateName;
	    return this;
	}
	
	public Builder withFantasyName(String fantasyName) {
	    this.fantasyName = fantasyName;
	    return this;
	}
	
	public Builder withFone(String fone) {
	    this.fone = fone;
	    return this;
	}
	
	public Builder withEmail(String email) {
	    this.email = email;
	    return this;
	}
	
	public Builder withAddressData(Address address) {
	    this.address = address;
	    return this;
	}
	
	public OthersServiceTaker build() {
	    return new OthersServiceTaker(this);
	    
	}
	
    }
    
    public OthersServiceTaker() {
	super(VALUE);
	this.cnpj = null;
	this.cpf = null;
	this.ie = null;
	this.corporateName = null;
	this.fantasyName = null;
	this.fone = null;
	this.email = null;
	this.address = null;
    }
    
    public OthersServiceTaker(Builder builder) {
	super(VALUE);
	this.cnpj = builder.cnpj;
	this.cpf = builder.cpf;
	this.ie = builder.ie;
	this.corporateName = builder.corporateName;
	this.fantasyName = builder.fantasyName;
	this.fone = builder.fone;
	this.email = builder.email;
	this.address = builder.address;
    }
    
    public String getCnpj() {
	return this.cnpj;
    }
    
    public String getCpf() {
	return this.cpf;
    }
    
    public String getIe() {
	return this.ie;
    }
    
    public String getCorporateName() {
	return this.corporateName;
    }
    
    public String getFantasyName() {
	return this.fantasyName;
    }
    
    public String getFone() {
	return this.fone;
    }
    
    public String getEmail() {
	return this.email;
    }
    
    public Address getAddress() {
	return this.address;
    }
}
