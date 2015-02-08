package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Tributação pelo COFINS 05 - Operação Tributável (ST)
 * 
 * @see BaseCOFINSNT
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */

//TODO Verificar esse CST, pois aparentemente não deveria estar no grupo de não tributado, e sim deveria ser o CST do grupo COFINSST (Nele não existe CST atualmente)
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS05 extends BaseCOFINSNT{

  private static final long serialVersionUID = 1L;
    
    public static class Builder extends BaseCOFINSNT.Builder implements COFINSBuilder {                
      
        @Override
        public COFINS05 build() {
            return new COFINS05(this);
        }
    }

    protected COFINS05(COFINS05.Builder builder) {
        super(builder, "05");
    }
    
}
