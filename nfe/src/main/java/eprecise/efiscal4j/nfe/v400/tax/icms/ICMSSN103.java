
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=103 - Isenção do ICMS no Simples Nacional para faixa de receita bruta.
 * 
 * @see BaseICMSSN102
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSSN103 extends BaseICMSSN102 {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSSN102.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSSN103.Builder) super.withOrigin(origin);
        }

        @Override
        public ICMSSN103 build() {
            return new ICMSSN103(this);
        }

    }

    public ICMSSN103() {
    }

    protected ICMSSN103(final ICMSSN103.Builder builder) {
        super(builder, "103");
    }
}
