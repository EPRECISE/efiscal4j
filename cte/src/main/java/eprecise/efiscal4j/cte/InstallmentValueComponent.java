package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.types.TypeDecimal1302;

public class InstallmentValueComponent implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "xNome") String name;
    
    private final @XmlElement(name = "vComp") @TypeDecimal1302 String value;
    
    public static class Builder {
	
	private String name;
	
	private String value;
	
	public Builder withName(String name) {
	    this.name = name;
	    return this;
	}
	
	public Builder withValue(String value) {
	    this.value = value;
	    return this;
	}
	
	public InstallmentValueComponent builder() {
	    return new InstallmentValueComponent(this);
	}
	
    }
    
    public InstallmentValueComponent() {
	this.name = null;
	this.value = null;
    }
    
    public InstallmentValueComponent(Builder builder) {
	this.name = builder.name;
	this.value = builder.value;
    }
    
    public String getName() {
	return this.name;
    }
    
    public String getValue() {
	return this.value;
    }
    
}
