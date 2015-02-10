package eprecise.efiscal4j.cte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CTe")
@XmlAccessorType(XmlAccessType.FIELD)
public class CTe {
    private final @XmlElement(name = "infCte") CTeInfo info;
    
    public static class Builder {
	private CTeInfo info;
	
	public Builder withInfo(CTeInfo info) {
	    this.info = info;
	    return this;
	}
	
	public CTe build() {
	    return new CTe(this);
	}
    }
    
    public CTe() {
	this.info = null;
    }
    
    public CTe(Builder builder) {
	this.info = builder.info;
    }
    
    public CTeInfo getInfo() {
	return this.info;
    }
    
}
