package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.Location;
import eprecise.efiscal4j.cte.address.Address;
import eprecise.efiscal4j.cte.types.FormatRegistrationSUFRAMA;

/**
 * @author carlos gomes
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Addressee extends Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "enderDest") Address address;
    
    private final @XmlElement(name = "ISUF") @FormatRegistrationSUFRAMA String registrationSUFRAMA;
    
    private final @XmlElement(name = "locEnt") Location locationDelivery;
    
    public static class Builder extends Person.Builder {
	
	private Address address;
	
	private String registrationSUFRAMA;
	
	private Location locationDelivery;
	
	@Override
	public Person.Builder withAddress(Address address) {
	    this.address = address;
	    return this;
	}
	
	public Builder withRegistrationSUFRAMA(String registrationSUFRAMA) {
	    this.registrationSUFRAMA = registrationSUFRAMA;
	    return this;
	}
	
	public Builder withLocationDelivery(Location locationDelivery) {
	    this.locationDelivery = locationDelivery;
	    return this;
	}
	
	@Override
	public Addressee builder() {
	    return new Addressee(this);
	}
    }
    
    public Addressee() {
	super();
	this.address = null;
	this.registrationSUFRAMA = null;
	this.locationDelivery = null;
    }
    
    public Addressee(Builder builder) {
	super(builder);
	this.address = builder.address;
	this.registrationSUFRAMA = builder.registrationSUFRAMA;
	this.locationDelivery = builder.locationDelivery;
    }
    
    @Override
    public Address getAddress() {
	return this.address;
    }
    
    public String getRegistrationSUFRAMA() {
	return this.registrationSUFRAMA;
    }
    
    public Location getLocationDelivery() {
	return this.locationDelivery;
    }
}
