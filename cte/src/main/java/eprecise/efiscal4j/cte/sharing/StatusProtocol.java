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
public class StatusProtocol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "infProt") StatusProtocolInfo statusProtocolData;
    
    private final @XmlAttribute(name = "versao") @NotNull String version;
    
    public static class Builder {
	
	private StatusProtocolInfo statusProtocolData;
	
	private String version;
	
	public Builder withStatusProtocolData(StatusProtocolInfo statusProtocolData) {
	    this.statusProtocolData = statusProtocolData;
	    return this;
	}
	
	public Builder withVersion(String version) {
	    this.version = version;
	    return this;
	}
	
	public StatusProtocol build() {
	    return new StatusProtocol(this);
	}
	
    }
    
    public StatusProtocol() {
	this.statusProtocolData = null;
	this.version = null;
    }
    
    public StatusProtocol(Builder builder) {
	this.statusProtocolData = builder.statusProtocolData;
	this.version = builder.version;
    }
    
    public StatusProtocolInfo getStatusProtocolData() {
	return this.statusProtocolData;
    }
    
    public String getVersion() {
	return this.version;
    }
}
