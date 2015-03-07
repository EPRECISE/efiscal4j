
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 * 
 */
abstract class BaseICMSSN extends ICMS {

    private static final long serialVersionUID = 1L;

    static abstract class Builder {

        protected ProductOrigin origin;

        /**
         * @see ProductOrigin
         * @param origin
         * @return
         */
        public Builder withOrigin(ProductOrigin origin) {
            this.origin = origin;
            return this;
        }

        abstract BaseICMSSN build();
    }

    private @XmlElement(name = "CSOSN") @NotNull final String csosn;

    protected BaseICMSSN() {
        this.csosn = null;
    }

    protected BaseICMSSN(ProductOrigin origin, String csosn) {
        super(origin);
        this.csosn = csosn;
    }

    public String getCSOSN() {
        return this.csosn;
    }

}
