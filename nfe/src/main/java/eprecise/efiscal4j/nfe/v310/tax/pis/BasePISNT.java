
package eprecise.efiscal4j.nfe.v310.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Classe base para os PIS com CST não tributados (04, 05, 06, 07, 08 e 09)
 * 
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BasePISNT extends BasePIS {

    private static final long serialVersionUID = 1L;

    static abstract class Builder extends BasePIS.Builder {

        @Override
        abstract BasePISNT build();
    }

    public BasePISNT() {
    }

    protected BasePISNT(final Builder builder, final String cst) {
        super(cst);
    }

    @Override
    public String getPisValue() {
        return "0";
    }

}
