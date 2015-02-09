package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.address.UF;
import eprecise.efiscal4j.cte.types.Code;
import eprecise.efiscal4j.cte.types.FormatCNPJ;
import eprecise.efiscal4j.cte.types.FormatCPF;

/**
 * @author carlos gomes
 */
public class Location implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "CNPJ") @FormatCNPJ String cnpj;
    
    private final @XmlElement(name = "CPF") @FormatCPF String cpf;
    
    private final @XmlElement(name = "xNome") @Size(min = 1, max = 60) String corporateName;
    
    private final @XmlElement(name = "xLgr") @Size(min = 1, max = 255) String street;
    
    private final @XmlElement(name = "nro") @Size(min = 1, max = 60) String number;
    
    private final @XmlElement(name = "xCpl") @Size(min = 1, max = 60) String complement;
    
    private final @XmlElement(name = "xBairro") @Size(min = 1, max = 60) String district;
    
    private final @XmlElement(name = "cMun") @Code String cityCode;
    
    private final @XmlElement(name = "xMun") @Size(min = 1, max = 60) String cityName;
    
    private final @XmlElement(name = "UF") String uf;
    
    public static class Builder {
	
	private String cnpj;
	
	private String cpf;
	
	private String corporateName;
	
	private String street;
	
	private String number;
	
	private String complement;
	
	private String district;
	
	private String cityCode;
	
	private String cityName;
	
	private String uf;
	
	public Builder withCNPJ(String CNPJ) {
	    this.cnpj = CNPJ;
	    return this;
	}
	
	public Builder withCPF(String cpf) {
	    this.cpf = cpf;
	    return this;
	}
	
	public Builder withCorporateName(String corporateName) {
	    this.corporateName = corporateName;
	    return this;
	}
	
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
	
	public Builder withUF(UF uf) {
	    this.uf = uf.getAcronym();
	    return this;
	}
	
	public Location build() {
	    return new Location(this);
	}
    }
    
    public Location() {
	this.cnpj = null;
	this.cpf = null;
	this.corporateName = null;
	this.street = null;
	this.number = null;
	this.complement = null;
	this.district = null;
	this.cityCode = null;
	this.cityName = null;
	this.uf = null;
    }
    
    public Location(Builder builder) {
	this.cnpj = builder.cnpj;
	this.cpf = builder.cpf;
	this.corporateName = builder.corporateName;
	this.street = builder.street;
	this.number = builder.number;
	this.complement = builder.complement;
	this.district = builder.district;
	this.cityCode = builder.cityCode;
	this.cityName = builder.cityName;
	this.uf = builder.uf;
    }
    
    public String getCnpj() {
	return this.cnpj;
    }
    
    public String getCpf() {
	return this.cpf;
    }
    
    public String getCorporateName() {
	return this.corporateName;
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
    
    public UF getUf() {
	return this.uf == null || this.uf.isEmpty() ? null : UF.valueOf(this.uf);
    }
}
