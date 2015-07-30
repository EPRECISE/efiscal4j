
package eprecise.efiscal4j.nfe.tax.icms;

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
class ICMSSN400 extends BaseICMSSN102 {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(ProductOrigin origin) {
            return (ICMSSN400.Builder) super.withOrigin(origin);
        }

        @Override
        public ICMSSN400 build() {
            return new ICMSSN400(this);
        }

    }

    protected ICMSSN400() {
        super();
    }

    protected ICMSSN400(ICMSSN400.Builder builder) {
        super(builder, "400");
    }
}
