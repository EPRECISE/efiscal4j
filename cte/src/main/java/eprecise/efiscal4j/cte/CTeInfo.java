package eprecise.efiscal4j.cte;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.person.Addressee;
import eprecise.efiscal4j.cte.person.Receiver;
import eprecise.efiscal4j.cte.person.Sender;
import eprecise.efiscal4j.cte.person.Shipper;
import eprecise.efiscal4j.cte.types.CTeID;

@XmlAccessorType(XmlAccessType.FIELD)
public class CTeInfo {
    
    private final @XmlElement(name = "ide") Identification identification;
    
    private final @XmlElement(name = "emit") Emitter emitter;
    
    private final @XmlElement(name = "rem") Sender sender;
    
    private final @XmlElement(name = "exped") Shipper shipper;
    
    private final @XmlElement(name = "receb") Receiver receiver;
    
    private final @XmlElement(name = "dest") Addressee addressee;
    
    private final @XmlElement(name = "vPrest") ValuesServiceDelivery valuesServiceDelivery;
    
    private final @XmlAttribute(name = "versao") @NotNull String version;
    
    private final @XmlAttribute(name = "Id") @NotNull @CTeID String id;
    
    public static class Builder {
	
	private Identification identification;
	
	private Emitter emitter;
	
	private ValuesServiceDelivery valuesServiceDelivery;
	
	private Sender sender;
	
	private Shipper shipper;
	
	private Receiver receiver;
	
	private Addressee addressee;
	
	private String version = "2.00";
	
	private String id;
	
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
	
	public Builder withShipper(Shipper shipper) {
	    this.shipper = shipper;
	    return this;
	}
	
	public Builder withReceiver(Receiver receiver) {
	    this.receiver = receiver;
	    return this;
	}
	
	public Builder withAddressee(Addressee addressee) {
	    this.addressee = addressee;
	    return this;
	}
	
	public Builder withVersion(String version) {
	    this.version = version;
	    return this;
	}
	
	public Builder withID(String id) {
	    this.id = id;
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
	this.shipper = null;
	this.receiver = null;
	this.addressee = null;
	this.version = null;
	this.id = null;
    }
    
    public CTeInfo(Builder builder) {
	this.identification = builder.identification;
	this.emitter = builder.emitter;
	this.valuesServiceDelivery = builder.valuesServiceDelivery;
	this.sender = builder.sender;
	this.shipper = builder.shipper;
	this.receiver = builder.receiver;
	this.addressee = builder.addressee;
	this.version = builder.version;
	this.id = builder.id;
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
    
    public Shipper getShipper() {
	return this.shipper;
    }
    
    public Receiver getReceiver() {
	return this.receiver;
    }
    
    public Addressee getAddressee() {
	return this.addressee;
    }
    
    public String getVersion() {
	return this.version;
    }
    
    public String getId() {
	return this.id;
    }
}
