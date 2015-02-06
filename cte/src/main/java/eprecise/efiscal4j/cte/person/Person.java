package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.types.FormatCNPJ;
import eprecise.efiscal4j.cte.types.FormatCPF;
import eprecise.efiscal4j.cte.types.StateRegistration;
import eprecise.efiscal4j.cte.types.TypeEmail;
import eprecise.efiscal4j.cte.types.TypeFone;

public abstract class Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "CNPJ") @FormatCNPJ String cnpj;
    
    private final @XmlElement(name = "CPF") @FormatCPF String cpf;
    
    private final @XmlElement(name = "IE") @StateRegistration String ie;
    
    private final @XmlElement(name = "xNome") @Size(min = 1, max = 60) String name;
    
    private final @XmlElement(name = "fone") @TypeFone String fone;
    
    private final @XmlElement(name = "email") @TypeEmail @Size(min = 1, max = 60) String email;
    
    public abstract static class Builder {
	
	private String cnpj;
	
	private String cpf;
	
	private String ie;
	
	private String name;
	
	private String fone;
	
	private String email;
	
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
	
	public Builder withName(String name) {
	    this.name = name;
	    return this;
	}
	
	public Builder withFone(String fone) {
	    this.fone = fone;
	    return this;
	}
	
	public abstract Builder withAddress(Address address);
	
	public Builder withEmail(String email) {
	    this.email = email;
	    return this;
	}
	
	public abstract Person builder();
	
    }
    
    public Person() {
	this.cnpj = null;
	this.cpf = null;
	this.ie = null;
	this.name = null;
	this.fone = null;
	this.email = null;
    }
    
    public Person(Builder builder) {
	this.cnpj = builder.cnpj;
	this.cpf = builder.cpf;
	this.ie = builder.ie;
	this.name = builder.name;
	this.fone = builder.fone;
	this.email = builder.email;
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
    
    public String getName() {
	return this.name;
    }
    
    public String getFone() {
	return this.fone;
    }
    
    public abstract Address getAddress();
    
    public String getEmail() {
	return this.email;
    }
    
}
