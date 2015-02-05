package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.types.TypeDecimal1302;

/**
 * Tag - vPrest
 * 
 * @author Carlos Gomes
 * 
 */

public class ValuesServiceDelivery implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "vTPrest") @TypeDecimal1302 String deliveryService;
    
    private final @XmlElement(name = "vRec") @TypeDecimal1302 String valueReceivable;
    
    private final @XmlElement(name = "Comp") InstallmentValueComponent installmentValueComponent;
    
    public static class Builder {
	
	private String delivertService;
	
	private String valueReceivable;
	
	private InstallmentValueComponent installmentValueComponent;
	
	public Builder withDelivertService(String delivertService) {
	    this.delivertService = delivertService;
	    return this;
	}
	
	public Builder withValueReceivable(String valueReceivable) {
	    this.valueReceivable = valueReceivable;
	    return this;
	}
	
	public Builder withComponents(InstallmentValueComponent installmentValueComponent) {
	    this.installmentValueComponent = installmentValueComponent;
	    return this;
	}
	
    }
    
    public ValuesServiceDelivery() {
	this.deliveryService = null;
	this.valueReceivable = null;
	this.installmentValueComponent = null;
    }
    
    public ValuesServiceDelivery(Builder builder) {
	this.deliveryService = builder.delivertService;
	this.valueReceivable = builder.valueReceivable;
	this.installmentValueComponent = builder.installmentValueComponent;
    }
    
    public String getDelivertService() {
	return this.deliveryService;
    }
    
    public String getValueReceivable() {
	return this.valueReceivable;
    }
    
    public InstallmentValueComponent getInstallmentValueComponent() {
	return this.installmentValueComponent;
    }
}
