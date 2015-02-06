package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.Location;
import eprecise.efiscal4j.cte.address.Address;

/**
 * 
 * tag - rem
 * 
 * @author carlos gomes
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Sender extends Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "enderReme") Address address;
    
    private final @XmlElement(name = "xFant") @Size(min = 1, max = 60) String fantasyName;
    
    private final @XmlElement(name = "locColeta") Location locationCollection;
    
    public static class Builder extends Person.Builder<Builder> {
	
	private Location locationCollection;
	
	private Address address;
	
	private String fantasyName;
	
	public Builder withLocationCollection(Location locationCollection) {
	    this.locationCollection = locationCollection;
	    return this;
	}
	
	public Builder withFantasyName(String fantasyName) {
	    this.fantasyName = fantasyName;
	    return this;
	}
	
	@Override
	public Builder withAddress(Address address) {
	    this.address = address;
	    return this;
	}
	
	@Override
	public Sender build() {
	    return new Sender(this);
	}
	
    }
    
    public Sender() {
	super();
	this.address = null;
	this.locationCollection = null;
	this.fantasyName = null;
    }
    
    public Sender(Builder builder) {
	super(builder);
	this.address = builder.address;
	this.locationCollection = builder.locationCollection;
	this.fantasyName = builder.fantasyName;
    }
    
    public Location getLocationCollection() {
	return this.locationCollection;
    }
    
    @Override
    public Address getAddress() {
	return this.address;
    }
    
    public String getFantasyName() {
	return this.fantasyName;
    }
    
}
