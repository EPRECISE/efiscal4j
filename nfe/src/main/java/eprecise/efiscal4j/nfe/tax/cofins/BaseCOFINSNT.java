
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Classe base para os COFINS com CST não tributados (04, 05, 06, 07, 08 e 09)
 * 
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BaseCOFINSNT extends BaseCOFINS {

    private static final long serialVersionUID = 1L;

    static abstract class Builder extends BaseCOFINS.Builder {

        @Override
        abstract BaseCOFINSNT build();
    }

    protected BaseCOFINSNT() {
        super();
    }

    protected BaseCOFINSNT(Builder builder, String cst) {
        super(cst);
    }

    @Override
    public String getCofinsValue() {
        return "0";
    }

}
