package eprecise.efiscal4j.cte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Detalhamento do CT-e do tipo Anulação
 * 
 * tag infCTeComp
 * 
 * @author Clecius J. Martinkoski
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CTeAnnulmentInformation extends CTeInformation {
    
    private static final long serialVersionUID = 1L;
    
    public class Builder extends CTeInformation.Builder {
	public CTeAnnulmentInformation build() {
	    return new CTeAnnulmentInformation(this);
	}
    }
    
    public CTeAnnulmentInformation() {
    }
    
    public CTeAnnulmentInformation(Builder builder) {
    }
    
}
