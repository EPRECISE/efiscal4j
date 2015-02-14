package eprecise.efiscal4j.cte.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author carlos
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeStatusProtocol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "infProt") StatusProtocolData statusProtocolData;
    
    private final @XmlAttribute(name = "versao") @NotNull String version;
    
    public static class Builder {
	
	private StatusProtocolData statusProtocolData;
	
	private String version;
	
	public Builder withStatusProtocolData(StatusProtocolData statusProtocolData) {
	    this.statusProtocolData = statusProtocolData;
	    return this;
	}
	
	public Builder withVersion(String version) {
	    this.version = version;
	    return this;
	}
	
	public TypeStatusProtocol build() {
	    return new TypeStatusProtocol(this);
	}
	
    }
    
    public TypeStatusProtocol() {
	this.statusProtocolData = null;
	this.version = null;
    }
    
    public TypeStatusProtocol(Builder builder) {
	this.statusProtocolData = builder.statusProtocolData;
	this.version = builder.version;
    }
    
    public StatusProtocolData getStatusProtocolData() {
	return this.statusProtocolData;
    }
    
    public String getVersion() {
	return this.version;
    }
}
