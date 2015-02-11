package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.address.AddressGeneral;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "cpf", "cnpj", "ie", "name" })
public abstract class Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "CPF") String cpf;
    
    private final @XmlElement(name = "CNPJ") String cnpj;
    
    private final @XmlElement(name = "IE") String ie;
    
    private final @XmlElement(name = "xNome") @Size(min = 1, max = 60) String name;
    
    @SuppressWarnings("unchecked")
    public abstract static class Builder<T extends Builder<?>> {
	
	private AbstractDocuments documents;
	
	private String name;
	
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
	
	public abstract T withAddress(AddressGeneral address);
	
	public abstract Person build();
	
    }
    
    public Person() {
	this.cnpj = null;
	this.cpf = null;
	this.ie = null;
	this.name = null;
	
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
	    this.ie = naturalPersonDocuments.getIe();
	    this.cnpj = null;
	} else {
	    this.cnpj = null;
	    this.cpf = null;
	    this.ie = null;
	}
	this.name = builder.name;
	
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public AbstractDocuments getDocuments() {
	if (this.isLegalEntity()) {
	    return new LegalEntityDocuments(new LegalEntityDocuments.Builder(null).withCNPJ(this.cnpj).withIE(this.ie));
	} else if (this.isNaturalPerson()) {
	    return new NaturalPersonDocuments(new NaturalPersonDocuments.Builder(null).withCPF(this.cpf).withIE(this.ie));
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
    
    public abstract Address getAddress();
    
}
