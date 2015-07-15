package eprecise.efiscal4j.cte;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.types.TypeDecimal1302;

/**
 * Informações da Carga do CT-e
 * 
 * tag infCarga
 * 
 * @author Clecius J. Martinkoski
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CargoInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Valor total da carga
     * 
     * Dever ser informado para todos os modais, com exceção para o Dutoviário.
     */
    private final @XmlElement(name = "vCarga") @TypeDecimal1302 String cargoValue;
    
    /**
     * Produto predominante
     * 
     * Informar a descrição do produto predominante
     */
    private final @XmlElement(name = "proPred") @NotNull @Size(min = 1, max = 60) String predominantProduct;
    
    /**
     * Outras características da carga
     * 
     * "FRIA", "GRANEL", "REFRIGERADA", "Medidas: 12X12X12"
     */
    private final @XmlElement(name = "xOutCat") @Valid @Size(min = 1, max = 30) String otherCargoInfo;
    
    /**
     * Informações de quantidades da Carga do CT-e
     * 
     * Para o Aéreo é obrigatório o preenchimento desse campo da seguinte forma.
     * 
     * 1 - Peso Bruto, sempre em quilogramas (obrigatório);
     * 
     * 2 - Peso Cubado; sempre em quilogramas;
     * 
     * 3 - Quantidade de volumes, sempre em unidades (obrigatório);
     * 
     * 4 - Cubagem, sempre em metros cúbicos (obrigatório apenas quando for
     * impossível preencher as dimensões da(s) embalagem(ens) na tag xDime do
     * leiaute do Aéreo).
     * 
     */
    private final @XmlElement(name = "infQ") List<CargoQuantityInfo> cargoQuantityInfos;
    
    public static class Builder {
	private String cargoValue;
	private String predominantProduct;
	private String otherCargoInfo;
	private List<CargoQuantityInfo> cargoQuantityInfos = Collections.emptyList();
	
	public Builder withCargoValue(String cargoValue) {
	    this.cargoValue = cargoValue;
	    return this;
	}
	
	public Builder withPredominatProduct(String predominantProduct) {
	    this.predominantProduct = predominantProduct;
	    return this;
	}
	
	public Builder withOtherCargoInfo(String otherCargoInfo) {
	    this.otherCargoInfo = otherCargoInfo;
	    return this;
	}
	
	public Builder withQuantityInfos(List<CargoQuantityInfo> quantityInfo) {
	    this.cargoQuantityInfos = quantityInfo;
	    return this;
	}
	
	public Builder withQuantityInfos(CargoQuantityInfo... quantityInfo) {
	    return this.withQuantityInfos(Arrays.asList(quantityInfo));
	}
	
	public CargoInfo build() {
	    return new CargoInfo(this);
	}
    }
    
    public CargoInfo() {
	this.cargoValue = null;
	this.predominantProduct = null;
	this.otherCargoInfo = null;
	this.cargoQuantityInfos = Collections.emptyList();
    }
    
    public CargoInfo(Builder builder) {
	this.cargoValue = builder.cargoValue;
	this.predominantProduct = builder.predominantProduct;
	this.otherCargoInfo = builder.otherCargoInfo;
	this.cargoQuantityInfos = builder.cargoQuantityInfos;
    }
    
    public String getCargoValue() {
	return this.cargoValue;
    }
    
    public String getPredominantProduct() {
	return this.predominantProduct;
    }
    
    public String getOtherCargoInfo() {
	return this.otherCargoInfo;
    }
    
    public List<CargoQuantityInfo> getCargoQuantityInfos() {
	return Collections.unmodifiableList(this.cargoQuantityInfos);
    }
}
