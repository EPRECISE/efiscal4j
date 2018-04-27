
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * @author Clécius J. Martinkoski
 * 
 */
abstract class BaseICMS extends ICMS {

    private static final long serialVersionUID = 1L;

    static abstract class Builder {

        protected ProductOrigin origin;

        /**
         * @see ProductOrigin
         * @param origin
         * @return
         */
        public Builder withOrigin(final ProductOrigin origin) {
            this.origin = origin;
            return this;
        }

        abstract BaseICMS build();

    }

    private @XmlElement(name = "CST") @NotNull final String cst;

    public BaseICMS() {
        this.cst = null;
    }

    protected BaseICMS(final ProductOrigin origin, final String cst) {
        super(origin);
        this.cst = cst;
    }

    @Override
    public String getCST() {
        return this.cst;
    }

}
