
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * @see COFINS
 * @author Felipe Bueno
 * 
 */
abstract class BaseCOFINS extends COFINS {

    private static final long serialVersionUID = 1L;

    static abstract class Builder {

        abstract BaseCOFINS build();

    }

    private @XmlElement(name = "CST") @NotNull final String cst;

    public BaseCOFINS() {
        super();
        this.cst = null;
    }

    protected BaseCOFINS(final String cst) {
        super();
        this.cst = cst;
    }

    public String getCST() {
        return this.cst;
    }

}
