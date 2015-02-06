package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.address.Address;

/**
 * 
 * tag - receb
 * 
 * @author carlos
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Receiver extends Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "enderReceb") Address address;
    
    public static class Builder extends Person.Builder {
	
	private Address address;
	
	@Override
	public Person.Builder withAddress(Address address) {
	    this.address = address;
	    return this;
	}
	
	@Override
	public Receiver builder() {
	    return new Receiver(this);
	}
    }
    
    public Receiver() {
	super();
	this.address = null;
    }
    
    public Receiver(Builder builder) {
	super(builder);
	this.address = builder.address;
    }
    
    @Override
    public Address getAddress() {
	return this.address;
    }
}
