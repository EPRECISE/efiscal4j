
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=300 - Imune.
 * 
 * @see BaseICMSSN102
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSSN300 extends BaseICMSSN102 {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSSN300.Builder) super.withOrigin(origin);
        }

        @Override
        public ICMSSN300 build() {
            return new ICMSSN300(this);
        }

    }

    public ICMSSN300() {
    }

    protected ICMSSN300(final ICMSSN300.Builder builder) {
        super(builder, "300");
    }
}
