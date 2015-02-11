package eprecise.efiscal4j.cte.person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.caelum.stella.bean.validation.CPF;
import eprecise.efiscal4j.cte.types.FormatCPF;
import eprecise.efiscal4j.cte.types.StateRegistration;

@XmlAccessorType(XmlAccessType.FIELD)
public class NaturalPersonDocuments extends AbstractDocuments {
    
    private static final long serialVersionUID = 1L;
    
    private @XmlElement(name = "CPF") @NotNull @Size(max = 11) @CPF(formatted = false) @FormatCPF final String cpf;
    
    private final @XmlElement(name = "IE") @StateRegistration String ie;
    
    public static class Builder<J extends Person.Builder<?>> extends AbstractDocuments.Builder<Builder<J>, J> {
	
	private String cpf;
	private String ie;
	
	public Builder(J personBuilder) {
	    super(personBuilder);
	}
	
	public Builder<J> withCPF(String cpf) {
	    this.cpf = cpf;
	    return this;
	}
	
	public Builder<J> withIE(String ie) {
	    this.ie = ie;
	    return this;
	}
	
	@Override
	public J ok() {
	    this.personBuilder.withDocuments(new NaturalPersonDocuments(this));
	    return this.personBuilder;
	}
	
    }
    
    public NaturalPersonDocuments() {
	this.cpf = null;
	this.ie = null;
    }
    
    public NaturalPersonDocuments(Builder<?> builder) {
	this.cpf = builder.cpf;
	this.ie = builder.ie;
    }
    
    public String getCpf() {
	return this.cpf;
	
    }
    
    public String getIe() {
	return this.ie;
    }
    
    @Override
    public boolean equals(Object object) {
	if (object instanceof NaturalPersonDocuments) {
	    final NaturalPersonDocuments another = (NaturalPersonDocuments) object;
	    return new EqualsBuilder().append(this.cpf, another.cpf).append(this.ie, another.ie).isEquals();
	}
	return false;
    }
    
    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(this.cpf).append(this.ie).toHashCode();
    }
    
}
