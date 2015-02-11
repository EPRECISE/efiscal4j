package eprecise.efiscal4j.cte.address;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.types.Code;
import eprecise.efiscal4j.cte.types.ZipCode;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Address implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "xLgr") @Size(min = 1, max = 255) String street;
    
    private final @XmlElement(name = "nro") @Size(min = 1, max = 60) String number;
    
    private final @XmlElement(name = "xCpl") @Size(min = 1, max = 60) String complement;
    
    private final @XmlElement(name = "xBairro") @Size(min = 1, max = 60) String district;
    
    private final @XmlElement(name = "cMun") @Code String cityCode;
    
    private final @XmlElement(name = "xMun") @Size(min = 1, max = 60) String cityName;
    
    private final @XmlElement(name = "CEP") @ZipCode String zipCode;
    
    private final @XmlElement(name = "UF") String uf;
    
    @SuppressWarnings("unchecked")
    public abstract static class Builder<T extends Builder<?>> {
	
	private String street;
	
	private String number;
	
	private String complement;
	
	private String district;
	
	private String cityCode;
	
	private String cityName;
	
	private String zipCode;
	
	private String uf;
	
	public T withStreet(String street) {
	    this.street = street;
	    return (T) this;
	}
	
	public T withNumber(String number) {
	    this.number = number;
	    return (T) this;
	}
	
	public T withComplement(String complement) {
	    this.complement = complement;
	    return (T) this;
	}
	
	public T withDistrict(String district) {
	    this.district = district;
	    return (T) this;
	}
	
	public T withCityCode(String cityCode) {
	    this.cityCode = cityCode;
	    return (T) this;
	}
	
	public T withCityName(String cityName) {
	    this.cityName = cityName;
	    return (T) this;
	}
	
	public T withZipCode(String zipCode) {
	    this.zipCode = zipCode;
	    return (T) this;
	}
	
	public T withUF(UF uf) {
	    this.uf = uf.getAcronym();
	    return (T) this;
	}
	
	public abstract Address build();
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
	
    }
    
    public Address(Builder<?> builder) {
	this.street = builder.street;
	this.number = builder.number;
	this.complement = builder.complement;
	this.district = builder.district;
	this.cityCode = builder.cityCode;
	this.cityName = builder.cityName;
	this.zipCode = builder.zipCode;
	this.uf = builder.uf;
	
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
    
}
