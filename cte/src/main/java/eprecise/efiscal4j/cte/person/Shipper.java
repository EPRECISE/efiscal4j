package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.address.Address;

/**
 * - tag - exped
 * 
 * @author carlos gomes
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Shipper extends Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "enderExped") Address address;
    
    public static class Builder extends Person.Builder<Builder> {
	
	private Address address;
	
	@Override
	public Builder withAddress(Address address) {
	    this.address = address;
	    return this;
	}
	
	@Override
	public Shipper build() {
	    return new Shipper(this);
	}
    }
    
    public Shipper() {
	super();
	this.address = null;
	
    }
    
    public Shipper(Builder builder) {
	super(builder);
	this.address = builder.address;
	
    }
    
    @Override
    public Address getAddress() {
	return this.address;
    }
    
}
