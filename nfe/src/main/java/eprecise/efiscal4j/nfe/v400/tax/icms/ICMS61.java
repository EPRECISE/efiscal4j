package eprecise.efiscal4j.nfe.v400.tax.icms;

import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1104;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;


/**
 * Tributação pelo ICMS 61 - Tributação monofásica do ICMS nas operações com combustíveis.
 *
 * @author Pedro H. Rodrigues
 * @see BaseICMS
 * @see ICMS
 */
@Getter
@NoArgsConstructor(force = true)
@XmlAccessorType(FIELD)
public class ICMS61 extends BaseICMS {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "qBCMonoRet")
    @NFeDecimal1104
    private final String qBCMonoRet;

    @XmlElement(name = "adRemICMSRet")
    @NFeDecimal0302a04
    private final String adRemICMSRet;

    @XmlElement(name = "vICMSMonoRet")
    @NFeDecimal1302
    private final String vICMSMonoRet;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private String qBCMonoRet;

        private String adRemICMSRet;

        private String vICMSMonoRet;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMS61.Builder) super.withOrigin(origin);
        }

        /**
         * Valor do ICMS retido anteriormente.
         */
        public Builder withVICMSMonoDif(final String vICMSMonoDif) {
            this.vICMSMonoRet = vICMSMonoDif;
            return this;
        }

        /**
         * Quantidade tributada retida anteriormente.
         */
        public Builder withQBCMonoRet(final String qBCMonoRet) {
            this.qBCMonoRet = qBCMonoRet;
            return this;
        }

        /**
         * Alíquota ad rem do imposto retido anteriormente.
         */
        public Builder withAdRemICMSRet(final String adRemICMSRet) {
            this.adRemICMSRet = adRemICMSRet;
            return this;
        }

        @Override
        public ICMS61 build() {
            return new ICMS61(this);
        }

    }

    protected ICMS61(final ICMS61.Builder builder) {
        super(builder.origin, "61");
        this.vICMSMonoRet = builder.vICMSMonoRet;
        this.qBCMonoRet = builder.qBCMonoRet;
        this.adRemICMSRet = builder.adRemICMSRet;
    }

}
