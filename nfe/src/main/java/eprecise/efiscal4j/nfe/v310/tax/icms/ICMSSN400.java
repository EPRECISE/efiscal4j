
package eprecise.efiscal4j.nfe.v310.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=400 - Não tributada pelo Simples Nacional
 * 
 * @see BaseICMSSN102
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSSN400 extends BaseICMSSN102 {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSSN400.Builder) super.withOrigin(origin);
        }

        @Override
        public ICMSSN400 build() {
            return new ICMSSN400(this);
        }

    }

    public ICMSSN400() {
    }

    protected ICMSSN400(final ICMSSN400.Builder builder) {
        super(builder, "400");
    }
}
