package eprecise.efiscal4j.cte;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.types.TypeDecimal1104;

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
 * 
 * @author Clecius J. Martinkoski
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CargoQuantityInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * Código da Unidade de Medida
     */
    private final @XmlElement(name = "cUnid") @NotNull MeasureUnity measureUnity;
    /**
     * Tipo da Medida
     * 
     * Exemplos: PESO BRUTO, PESO DECLARADO, PESO CUBADO, PESO AFORADO, PESO
     * AFERIDO, PESO BASE DE CÁLCULO, LITRAGEM, CAIXAS e etc
     */
    private final @XmlElement(name = "tpMed") @NotNull @Size(min = 1, max = 20) String measureType;
    /**
     * Quantidade
     */
    private final @XmlElement(name = "qCarga") @NotNull @TypeDecimal1104 String quantity;
    
    public static class Builder {
	private MeasureUnity unity;
	private String measureType;
	private String quantity;
	
	public Builder withMeasureUnity(MeasureUnity unity) {
	    this.unity = unity;
	    return this;
	}
	
	public Builder withMeasureType(String measureType) {
	    this.measureType = measureType;
	    return this;
	}
	
	public Builder withQuantity(String quantity) {
	    this.quantity = quantity;
	    return this;
	}
	
	public Builder withQuantity(BigDecimal quantity) {
	    this.quantity = TypeDecimal1104.FORMAT.format(quantity);
	    return this;
	}
    }
    
    public CargoQuantityInfo() {
	this.measureUnity = null;
	this.measureType = null;
	this.quantity = null;
    }
    
    public CargoQuantityInfo(Builder builder) {
	this.measureUnity = builder.unity;
	this.measureType = builder.measureType;
	this.quantity = builder.quantity;
    }
    
    public MeasureUnity getMeasureUnity() {
	return this.measureUnity;
    }
    
    public String getMeasureType() {
	return this.measureType;
    }
    
    public BigDecimal getQuantity() {
	return Optional.ofNullable(this.quantity).map(s -> {
	    try {
		return TypeDecimal1104.FORMAT.parse(s);
	    } catch (final Exception e) {
		throw new RuntimeException(e);
	    }
	}).map(n -> BigDecimal.valueOf(n.doubleValue())).orElse(BigDecimal.ZERO);
    }
}
