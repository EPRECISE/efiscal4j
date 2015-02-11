package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Tributação pelo COFINS 08 - Operação Sem Incidência da contribuição
 * 
 * @see BaseCOFINSNT
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS08 extends BaseCOFINSNT{

    private static final long serialVersionUID = 1L;
    
    public static class Builder extends BaseCOFINSNT.Builder implements COFINSBuilder {                
      
        @Override
        public COFINS08 build() {
            return new COFINS08(this);
        }
    }

    protected COFINS08(COFINS08.Builder builder) {
        super(builder, "08");
    }
    
}
