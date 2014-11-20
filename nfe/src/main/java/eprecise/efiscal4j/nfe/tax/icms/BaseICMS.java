
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


abstract class BaseICMS extends ICMS {

    static abstract class Builder {

        abstract BaseICMS build();
    }

    private @XmlElement(name = "CST") @NotNull final String cst;

    protected BaseICMS(ProductOrigin origin, String cst) {
        super(origin);
        this.cst = cst;
    }

    public String getCST() {
        return this.cst;
    }

}
