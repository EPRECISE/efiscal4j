package eprecise.efiscal4j.cte.address;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.types.FormatCountryCode;

/**
 * @author carlos
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressGeneral extends Address implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "cPais") @FormatCountryCode String countryCode;
    
    private final @XmlElement(name = "xPais") @Size(min = 1, max = 60) String countryName;
    
    public static class Builder extends Address.Builder<Builder> {
	
	private String countryCode;
	
	private String countryName;
	
	public Builder withCountryCode(String countryCode) {
	    this.countryCode = countryCode;
	    return this;
	}
	
	public Builder withCountryName(String countryName) {
	    this.countryName = countryName;
	    return this;
	}
	
	@Override
	public AddressGeneral build() {
	    return new AddressGeneral(this);
	}
    }
    
    public AddressGeneral() {
	super();
	this.countryCode = null;
	this.countryName = null;
    }
    
    public AddressGeneral(Builder builder) {
	super(builder);
	this.countryCode = builder.countryCode;
	this.countryName = builder.countryName;
    }
    
    public String getCountryCode() {
	return this.countryCode;
    }
    
    public String getCountryName() {
	return this.countryName;
    }
}
