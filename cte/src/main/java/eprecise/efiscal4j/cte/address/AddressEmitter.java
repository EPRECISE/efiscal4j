package eprecise.efiscal4j.cte.address;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author carlos
 */
public class AddressEmitter extends Address implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "fone") String fone;
    
    public static class Builder extends Address.Builder<Builder> {
	
	private String fone;
	
	public Builder withFone(String fone) {
	    this.fone = fone;
	    return this;
	}
	
	@Override
	public AddressEmitter build() {
	    return new AddressEmitter(this);
	}
	
    }
    
    public AddressEmitter() {
	super();
	this.fone = null;
    }
    
    public AddressEmitter(Builder builder) {
	super(builder);
	this.fone = builder.fone;
    }
    
}
