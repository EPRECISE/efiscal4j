package eprecise.efiscal4j.cte.address;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.types.Code;
import eprecise.efiscal4j.cte.types.FormatCountryCode;
import eprecise.efiscal4j.cte.types.ZipCode;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "xLgr") @Size(min = 1, max = 255) String street;
    
    private final @XmlElement(name = "nro") @Size(min = 1, max = 60) String number;
    
    private final @XmlElement(name = "xCpl") @Size(min = 1, max = 60) String complement;
    
    private final @XmlElement(name = "xBairro") @Size(min = 1, max = 60) String district;
    
    private final @XmlElement(name = "cMun") @Code String cityCode;
    
    private final @XmlElement(name = "xMun") @Size(min = 1, max = 60) String cityName;
    
    private final @XmlElement(name = "CEP") @ZipCode String zipCode;
    
    private final @XmlElement(name = "UF") String uf;
    
    private final @XmlElement(name = "cPais") @FormatCountryCode String countryCode;
    
    private final @XmlElement(name = "xPais") @Size(min = 1, max = 60) String countryName;
    
    public static class Builder {
	
	private String street;
	
	private String number;
	
	private String complement;
	
	private String district;
	
	private String cityCode;
	
	private String cityName;
	
	private String zipCode;
	
	private String uf;
	
	private String countryCode;
	
	private String countryName;
	
	public Builder withStreet(String street) {
	    this.street = street;
	    return this;
	}
	
	public Builder withNumber(String number) {
	    this.number = number;
	    return this;
	}
	
	public Builder withComplement(String complement) {
	    this.complement = complement;
	    return this;
	}
	
	public Builder withDistrict(String district) {
	    this.district = district;
	    return this;
	}
	
	public Builder withCityCode(String cityCode) {
	    this.cityCode = cityCode;
	    return this;
	}
	
	public Builder withCityName(String cityName) {
	    this.cityName = cityName;
	    return this;
	}
	
	public Builder withZipCode(String zipCode) {
	    this.zipCode = zipCode;
	    return this;
	}
	
	public Builder withUF(UF uf) {
	    this.uf = uf.getAcronym();
	    return this;
	}
	
	public Builder withCountryCode(String countryCode) {
	    this.countryCode = countryCode;
	    return this;
	}
	
	public Builder withCountryName(String countryName) {
	    this.countryName = countryName;
	    return this;
	}
	
	public Address build() {
	    return new Address(this);
	}
    }
    
    public Address() {
	this.street = null;
	this.number = null;
	this.complement = null;
	this.district = null;
	this.cityCode = null;
	this.cityName = null;
	this.zipCode = null;
	this.uf = null;
	this.countryCode = null;
	this.countryName = null;
    }
    
    public Address(Builder builder) {
	this.street = builder.street;
	this.number = builder.number;
	this.complement = builder.complement;
	this.district = builder.district;
	this.cityCode = builder.cityCode;
	this.cityName = builder.cityName;
	this.zipCode = builder.zipCode;
	this.uf = builder.uf;
	this.countryCode = builder.countryCode;
	this.countryName = builder.countryName;
	
    }
    
    public String getStreet() {
	return this.street;
    }
    
    public String getNumber() {
	return this.number;
    }
    
    public String getComplement() {
	return this.complement;
    }
    
    public String getDistrict() {
	return this.district;
    }
    
    public String getCityCode() {
	return this.cityCode;
    }
    
    public String getCityName() {
	return this.cityName;
    }
    
    public String getZipCode() {
	return this.zipCode;
    }
    
    public UF getAcronymUf() {
	return this.uf == null || this.uf.isEmpty() ? null : UF.valueOf(this.uf);
    }
    
    public String getCountryCode() {
	return this.countryCode;
    }
    
    public String getCountryName() {
	return this.countryName;
    }
}
