package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.types.FormatCNPJ;
import eprecise.efiscal4j.cte.types.FormatCPF;
import eprecise.efiscal4j.cte.types.StateRegistration;
import eprecise.efiscal4j.cte.types.TypeEmail;
import eprecise.efiscal4j.cte.types.TypeFone;

public class Sender implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "TCnpjOpc") @FormatCNPJ String cnpj;
    
    private final @XmlElement(name = "CPF") @FormatCPF String cpf;
    
    private final @XmlElement(name = "IE") @StateRegistration String ie;
    
    private final @XmlElement(name = "xNome") @Size(min = 1, max = 60) String corporateName;
    
    private final @XmlElement(name = "xFant") @Size(min = 1, max = 60) String fantasyName;
    
    private final @XmlElement(name = "fone") @TypeFone String fone;
    
    private final @XmlElement(name = "enderReme") @TypeFone Address address;
    
    private final @XmlElement(name = "email") @TypeEmail @Size(min = 1, max = 60) String email;
    
    private final @XmlElement(name = "locColeta") LocationCollection locationCollection;
    
    public static class Builder {
	
	private String cnpj;
	
	private String cpf;
	
	private String ie;
	
	private String corporateName;
	
	private String fantasyName;
	
	private String fone;
	
	private Address address;
	
	private String email;
	
	private LocationCollection locationCollection;
	
	public Builder withCNPJ(String cnpj) {
	    this.cnpj = cnpj;
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
	
	public Builder withAddress(Address address) {
	    this.address = address;
	    return this;
	}
	
	public Builder withEmail(String email) {
	    this.email = email;
	    return this;
	}
	
	public Builder withLocationCollection(LocationCollection locationCollection) {
	    this.locationCollection = locationCollection;
	    return this;
	}
	
	public Sender builder() {
	    return new Sender(this);
	}
	
    }
    
    public Sender() {
	this.cnpj = null;
	this.cpf = null;
	this.ie = null;
	this.corporateName = null;
	this.fantasyName = null;
	this.fone = null;
	this.address = null;
	this.email = null;
	this.locationCollection = null;
    }
    
    public Sender(Builder builder) {
	this.cnpj = builder.cnpj;
	this.cpf = builder.cpf;
	this.ie = builder.ie;
	this.corporateName = builder.corporateName;
	this.fantasyName = builder.fantasyName;
	this.fone = builder.fone;
	this.address = builder.address;
	this.email = builder.email;
	this.locationCollection = builder.locationCollection;
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
    
    public Address getAddress() {
	return this.address;
    }
    
    public String getEmail() {
	return this.email;
    }
    
    public LocationCollection getLocationCollection() {
	return this.locationCollection;
    }
    
}
