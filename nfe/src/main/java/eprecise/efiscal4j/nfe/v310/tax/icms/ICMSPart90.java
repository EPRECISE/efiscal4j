
package eprecise.efiscal4j.nfe.v310.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.commons.domain.adress.UF;


/**
 * Partilha do ICMS entre a UF de origem e UF de destino ou a UF definida na legislação Operação interestadual para consumidor final com partilha do ICMS devido na operação entre a UF de origem e a UF
 * do destinatário ou ou a UF definida na legislação. (Ex. UF da concessionária de entrega do veículos)
 * 
 * CST 90 - Outros.
 * 
 * @see BaseICMSPart
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSPart90 extends BaseICMSPart {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSPart.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSPart90.Builder) super.withOrigin(origin);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcModality(final BCModality bcModality) {
            return (ICMSPart90.Builder) super.withBcModality(bcModality);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcReductionPercent(final String bcReductionPercent) {
            return (ICMSPart90.Builder) super.withBcReductionPercent(bcReductionPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (ICMSPart90.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsAliquot(final String icmsAliquot) {
            return (ICMSPart90.Builder) super.withIcmsAliquot(icmsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsValue(final String icmsValue) {
            return (ICMSPart90.Builder) super.withIcmsValue(icmsValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withValueMarginAddedStPercent(final String valueMarginAddedStPercent) {
            return (ICMSPart90.Builder) super.withValueMarginAddedStPercent(valueMarginAddedStPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcReductionStPercent(final String bcReductionStPercent) {
            return (ICMSPart90.Builder) super.withBcReductionStPercent(bcReductionStPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcModalityST(final BCModalityST bcModalityST) {
            return (ICMSPart90.Builder) super.withBcModalityST(bcModalityST);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValueST(final String bcValueST) {
            return (ICMSPart90.Builder) super.withBcValueST(bcValueST);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsStAliquot(final String icmsStAliquot) {
            return (ICMSPart90.Builder) super.withIcmsStAliquot(icmsStAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsStValue(final String icmsStValue) {
            return (ICMSPart90.Builder) super.withIcmsStValue(icmsStValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withSelfOperationBCPerc(final String selfOperationBCPerc) {
            return (ICMSPart90.Builder) super.withSelfOperationBCPerc(selfOperationBCPerc);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUfST(final UF ufST) {
            return (ICMSPart90.Builder) super.withUfST(ufST);
        }

        @Override
        public ICMSPart90 build() {
            return new ICMSPart90(this);
        }

    }

    public ICMSPart90() {
    }

    protected ICMSPart90(final ICMSPart90.Builder builder) {
        super(builder, "90");
    }
}
