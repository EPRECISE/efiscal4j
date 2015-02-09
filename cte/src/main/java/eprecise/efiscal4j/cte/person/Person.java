package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.types.TypeEmail;
import eprecise.efiscal4j.cte.types.TypeFone;

public abstract class Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "CPF") String cpf;
    
    private final @XmlElement(name = "CNPJ") String cnpj;
    
    private final @XmlElement(name = "IE") String ie;
    
    private final @XmlElement(name = "xNome") @Size(min = 1, max = 60) String name;
    
    private final @XmlElement(name = "fone") @TypeFone String fone;
    
    private final @XmlElement(name = "email") @TypeEmail @Size(min = 1, max = 60) String email;
    
    @SuppressWarnings("unchecked")
    public abstract static class Builder<T extends Builder<?>> {
	
	private AbstractDocuments documents;
	
	private String name;
	
	private String fone;
	
	private String email;
	
	T withDocuments(AbstractDocuments documents) {
	    this.documents = documents;
	    return (T) this;
	}
	
	public LegalEntityDocuments.Builder<T> asLegalEntity() {
	    return (eprecise.efiscal4j.cte.person.LegalEntityDocuments.Builder<T>) new LegalEntityDocuments.Builder<Builder<T>>(this);
	}
	
	public NaturalPersonDocuments.Builder<T> asNaturalPerson() {
	    return (eprecise.efiscal4j.cte.person.NaturalPersonDocuments.Builder<T>) new NaturalPersonDocuments.Builder<Builder<T>>(this);
	}
	
	public T withName(String name) {
	    this.name = name;
	    return (T) this;
	}
	
	public T withFone(String fone) {
	    this.fone = fone;
	    return (T) this;
	}
	
	public abstract T withAddress(Address address);
	
	public T withEmail(String email) {
	    this.email = email;
	    return (T) this;
	}
	
	public abstract Person build();
	
    }
    
    public Person() {
	this.cnpj = null;
	this.cpf = null;
	this.ie = null;
	this.name = null;
	this.fone = null;
	this.email = null;
    }
    
    public Person(Builder<?> builder) {
	if (builder.documents instanceof LegalEntityDocuments) {
	    final LegalEntityDocuments legalEntityDocuments = (LegalEntityDocuments) builder.documents;
	    this.cnpj = legalEntityDocuments.getCnpj();
	    this.ie = legalEntityDocuments.getIe();
	    this.cpf = null;
	} else if (builder.documents instanceof NaturalPersonDocuments) {
	    final NaturalPersonDocuments naturalPersonDocuments = (NaturalPersonDocuments) builder.documents;
	    this.cpf = naturalPersonDocuments.getCpf();
	    this.ie = null;
	    this.cnpj = null;
	} else {
	    this.cnpj = null;
	    this.cpf = null;
	    this.ie = null;
	}
	this.name = builder.name;
	this.fone = builder.fone;
	this.email = builder.email;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public AbstractDocuments getDocuments() {
	if (this.isLegalEntity()) {
	    return new LegalEntityDocuments(new LegalEntityDocuments.Builder(null).withCNPJ(this.cnpj).withIE(this.ie));
	} else if (this.isNaturalPerson()) {
	    return new NaturalPersonDocuments(new NaturalPersonDocuments.Builder(null).withCPF(this.cpf));
	} else {
	    return null;
	}
    }
    
    public <T extends AbstractDocuments> T getDocuments(Class<T> clazz) {
	return clazz.cast(this.getDocuments());
    }
    
    public boolean isLegalEntity() {
	return !StringUtils.isEmpty(this.cnpj);
    }
    
    public boolean isNaturalPerson() {
	return !StringUtils.isEmpty(this.cpf);
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
