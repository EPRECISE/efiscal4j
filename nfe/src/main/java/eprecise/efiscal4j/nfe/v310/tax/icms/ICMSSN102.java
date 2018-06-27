
package eprecise.efiscal4j.nfe.v310.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=102 - Tributada pelo Simples Nacional sem permissão de crédito.
 * 
 * @see BaseICMSSN102
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSSN102 extends BaseICMSSN102 {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSSN102.Builder) super.withOrigin(origin);
        }

        @Override
        public ICMSSN102 build() {
            return new ICMSSN102(this);
        }

    }

    public ICMSSN102() {
    }

    protected ICMSSN102(final ICMSSN102.Builder builder) {
        super(builder, "102");
    }
}
