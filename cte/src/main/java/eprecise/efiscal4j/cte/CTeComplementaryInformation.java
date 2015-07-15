package eprecise.efiscal4j.cte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Detalhamento do CT-e complementado
 * 
 * tag infCTeComp
 * 
 * @author Clecius J. Martinkoski
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CTeComplementaryInformation extends CTeInformation {
    
    private static final long serialVersionUID = 1L;
    
    public class Builder extends CTeInformation.Builder {
	
	public CTeComplementaryInformation build() {
	    return new CTeComplementaryInformation(this);
	}
	
    }
    
    public CTeComplementaryInformation() {
    }
    
    public CTeComplementaryInformation(Builder builder) {
    }
    
}
