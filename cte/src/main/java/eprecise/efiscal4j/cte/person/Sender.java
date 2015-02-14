package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.cte.Location;
import eprecise.efiscal4j.cte.address.AddressGeneral;
import eprecise.efiscal4j.cte.types.TypeEmail;
import eprecise.efiscal4j.cte.types.TypeFone;

/**
 * 
 * tag - rem
 * 
 * @author carlos gomes
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "fantasyName", "fone", "address", "email", "locationCollection" })
public class Sender extends Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "xFant") @Size(min = 1, max = 60) String fantasyName;
    
    private final @XmlElement(name = "fone") @TypeFone String fone;
    
    private final @XmlElement(name = "enderReme") AddressGeneral address;
    
    private final @XmlElement(name = "email") @TypeEmail @Size(min = 1, max = 60) String email;
    
    private final @XmlElement(name = "locColeta") Location locationCollection;
    
    public static class Builder extends Person.Builder<Builder> {
	
	private Location locationCollection;
	
	private AddressGeneral address;
	
	private String fantasyName;
	
	private String fone;
	
	private String email;
	
	public Builder withLocationCollection(Location locationCollection) {
	    this.locationCollection = locationCollection;
	    return this;
	}
	
	public Builder withFantasyName(String fantasyName) {
	    this.fantasyName = fantasyName;
	    return this;
	}
	
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
	public Sender build() {
	    return new Sender(this);
	}
	
    }
    
    public Sender() {
	super();
	this.address = null;
	this.locationCollection = null;
	this.fantasyName = null;
	this.fone = null;
	this.email = null;
    }
    
    public Sender(Builder builder) {
	super(builder);
	this.address = builder.address;
	this.locationCollection = builder.locationCollection;
	this.fantasyName = builder.fantasyName;
	this.fone = builder.fone;
	this.email = builder.email;
    }
    
    public Location getLocationCollection() {
	return this.locationCollection;
    }
    
    @Override
    public AddressGeneral getAddress() {
	return this.address;
    }
    
    public String getFantasyName() {
	return this.fantasyName;
    }
    
    public String getFone() {
	return this.fone;
    }
    
    public String getEmail() {
	return this.email;
    }
    
}
