package eprecise.efiscal4j.cte;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Grupo de informações do CT-e Normal e Substituto
 * 
 * tag infCTeNorm
 * 
 * @author Clecius J. Martinkoski
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CTeRegularInformation extends CTeInformation {
    
    private static final long serialVersionUID = 1L;
    
    private final @XmlElement(name = "infCarga") @Valid @NotNull CargoInfo cargoInfo;
    
    public static class Builder extends CTeInformation.Builder {
	private CargoInfo cargoInfo;
	
	public Builder withCargoInfo(CargoInfo cargoInfo) {
	    this.cargoInfo = cargoInfo;
	    return this;
	}
	
	public CTeRegularInformation build() {
	    return new CTeRegularInformation(this);
	}
    }
    
    public CTeRegularInformation() {
	this.cargoInfo = null;
    }
    
    public CTeRegularInformation(Builder builder) {
	this.cargoInfo = builder.cargoInfo;
    }
    
    public CargoInfo getCargoInfo() {
	return this.cargoInfo;
    }
    
}
