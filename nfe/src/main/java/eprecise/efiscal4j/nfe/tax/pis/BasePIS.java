
package eprecise.efiscal4j.nfe.tax.pis;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * @see PIS
 * @author Felipe Bueno
 * 
 */
abstract class BasePIS extends PIS {

    private static final long serialVersionUID = 1L;

    static abstract class Builder {

        abstract BasePIS build();

    }

    private @XmlElement(name = "CST") @NotNull final String cst;

    public BasePIS() {
        this.cst = null;
    }

    protected BasePIS(final String cst) {
        super();
        this.cst = cst;
    }

    public String getCST() {
        return this.cst;
    }

}
