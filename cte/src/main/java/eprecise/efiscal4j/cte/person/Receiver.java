package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.cte.address.AddressGeneral;
import eprecise.efiscal4j.cte.types.TypeEmail;
import eprecise.efiscal4j.cte.types.TypeFone;

/**
 * 
 * tag - receb
 * 
 * @author carlos
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "fone", "address", "email" })
public class Receiver extends Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "fone") @TypeFone String fone;
    
    private final @XmlElement(name = "enderReceb") AddressGeneral address;
    
    private final @XmlElement(name = "email") @TypeEmail @Size(min = 1, max = 60) String email;
    
    public static class Builder extends Person.Builder<Builder> {
	
	private AddressGeneral address;
	
	private String fone;
	
	private String email;
	
	@Override
	public Builder withAddress(AddressGeneral address) {
	    this.address = address;
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
	
	@Override
	public Receiver build() {
	    return new Receiver(this);
	}
    }
    
    public Receiver() {
	super();
	this.address = null;
	this.fone = null;
	this.email = null;
    }
    
    public Receiver(Builder builder) {
	super(builder);
	this.address = builder.address;
	this.fone = builder.fone;
	this.email = builder.email;
    }
    
    @Override
    public AddressGeneral getAddress() {
	return this.address;
    }
    
    public String getFone() {
	return this.fone;
    }
    
    public String getEmail() {
	return this.email;
    }
}
