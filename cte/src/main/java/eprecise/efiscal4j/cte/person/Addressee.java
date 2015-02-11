package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.cte.Location;
import eprecise.efiscal4j.cte.address.AddressGeneral;
import eprecise.efiscal4j.cte.types.FormatRegistrationSUFRAMA;
import eprecise.efiscal4j.cte.types.TypeEmail;
import eprecise.efiscal4j.cte.types.TypeFone;

/**
 * @author carlos gomes
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "fone", "registrationSUFRAMA", "address", "email", "locationDelivery" })
public class Addressee extends Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "fone") @TypeFone String fone;
    
    private final @XmlElement(name = "ISUF") @FormatRegistrationSUFRAMA String registrationSUFRAMA;
    
    private final @XmlElement(name = "enderDest") AddressGeneral address;
    
    private final @XmlElement(name = "email") @TypeEmail @Size(min = 1, max = 60) String email;
    
    private final @XmlElement(name = "locEnt") Location locationDelivery;
    
    public static class Builder extends Person.Builder<Builder> {
	
	private AddressGeneral address;
	
	private String registrationSUFRAMA;
	
	private Location locationDelivery;
	
	private String fone;
	
	private String email;
	
	@Override
	public Builder withAddress(AddressGeneral address) {
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
	
	public Builder withFone(String fone) {
	    this.fone = fone;
	    return this;
	}
	
	public Builder withEmail(String email) {
	    this.email = email;
	    return this;
	}
	
	@Override
	public Addressee build() {
	    return new Addressee(this);
	}
    }
    
    public Addressee() {
	super();
	this.address = null;
	this.registrationSUFRAMA = null;
	this.locationDelivery = null;
	this.fone = null;
	this.email = null;
    }
    
    public Addressee(Builder builder) {
	super(builder);
	this.address = builder.address;
	this.registrationSUFRAMA = builder.registrationSUFRAMA;
	this.locationDelivery = builder.locationDelivery;
	this.fone = builder.fone;
	this.email = builder.email;
    }
    
    @Override
    public AddressGeneral getAddress() {
	return this.address;
    }
    
    public String getRegistrationSUFRAMA() {
	return this.registrationSUFRAMA;
    }
    
    public Location getLocationDelivery() {
	return this.locationDelivery;
    }
    
    public String getFone() {
	return this.fone;
    }
    
    public String getEmail() {
	return this.email;
    }
}
