package eprecise.efiscal4j.cte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CTeInfo {
    
    private final @XmlElement(name = "ide") Identification identification;
    
    private final @XmlElement(name = "emit") Emitter emitter;
    
    private final @XmlElement(name = "vPrest") ValuesServiceDelivery valuesServiceDelivery;
    
    private final @XmlElement(name = "rem") Sender sender;
    
    public static class Builder {
	
	private Identification identification;
	
	private Emitter emitter;
	
	private ValuesServiceDelivery valuesServiceDelivery;
	
	private Sender sender;
	
	public Builder withIdentification(Identification identification) {
	    this.identification = identification;
	    return this;
	}
	
	public Builder withEmitter(Emitter emitter) {
	    this.emitter = emitter;
	    return this;
	}
	
	public Builder withValuesServiceDelivery(ValuesServiceDelivery valuesServiceDelivery) {
	    this.valuesServiceDelivery = valuesServiceDelivery;
	    return this;
	}
	
	public Builder withSender(Sender sender) {
	    this.sender = sender;
	    return this;
	}
	
	public CTeInfo build() {
	    return new CTeInfo(this);
	}
	
    }
    
    public CTeInfo() {
	this.identification = null;
	this.emitter = null;
	this.valuesServiceDelivery = null;
	this.sender = null;
    }
    
    public CTeInfo(Builder builder) {
	this.identification = builder.identification;
	this.emitter = builder.emitter;
	this.valuesServiceDelivery = builder.valuesServiceDelivery;
	this.sender = builder.sender;
    }
    
    public Identification getIdentification() {
	return this.identification;
    }
    
    public Emitter getEmitter() {
	return this.emitter;
    }
    
    public ValuesServiceDelivery getValuesServiceDelivery() {
	return this.valuesServiceDelivery;
    }
    
}
