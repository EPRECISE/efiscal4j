package eprecise.efiscal4j.cte.person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.cte.types.FormatCNPJ;
import eprecise.efiscal4j.cte.types.StateRegistration;

@XmlAccessorType(XmlAccessType.FIELD)
public class LegalEntityDocuments extends AbstractDocuments {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @FormatCNPJ String cnpj;
    
    private final @XmlElement(name = "IE") @StateRegistration String ie;
    
    public static class Builder<J extends Person.Builder<?>> extends AbstractDocuments.Builder<Builder<J>, J> {
	
	private String cnpj;
	private String ie;
	
	public Builder(J personBuilder) {
	    super(personBuilder);
	}
	
	public Builder<J> withCNPJ(String cnpj) {
	    this.cnpj = cnpj;
	    return this;
	}
	
	public Builder<J> withIE(String ie) {
	    this.ie = ie;
	    return this;
	}
	
	@Override
	public J ok() {
	    this.personBuilder.withDocuments(new LegalEntityDocuments(this));
	    return this.personBuilder;
	}
	
    }
    
    public LegalEntityDocuments() {
	this.cnpj = null;
	this.ie = null;
    }
    
    public LegalEntityDocuments(Builder<?> builder) {
	this.cnpj = builder.cnpj;
	this.ie = builder.ie;
    }
    
    public String getCnpj() {
	return this.cnpj;
    }
    
    public String getIe() {
	return this.ie;
    }
    
    @Override
    public boolean equals(Object object) {
	if (object instanceof LegalEntityDocuments) {
	    final LegalEntityDocuments another = (LegalEntityDocuments) object;
	    return new EqualsBuilder().append(this.cnpj, another.cnpj).append(this.ie, another.ie).isEquals();
	}
	return false;
    }
    
    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(this.cnpj).append(this.ie).toHashCode();
    }
    
}
