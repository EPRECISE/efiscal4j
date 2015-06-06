
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.commons.domain.adress.UF;


/**
 * Partilha do ICMS entre a UF de origem e UF de destino ou a UF definida na legislação Operação interestadual para consumidor final com partilha do ICMS devido na operação entre a UF de origem e a UF
 * do destinatário ou ou a UF definida na legislação. (Ex. UF da concessionária de entrega do veículos)
 * 
 * CST 10 - Tributada e com cobrança do ICMS por substituição tributária
 * 
 * @see BaseICMSPart
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSPart10 extends BaseICMSPart {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSPart.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(ProductOrigin origin) {
            return (ICMSPart10.Builder) super.withOrigin(origin);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcModality(BCModality bcModality) {
            return (ICMSPart10.Builder) super.withBcModality(bcModality);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcReductionPercent(String bcReductionPercent) {
            return (ICMSPart10.Builder) super.withBcReductionPercent(bcReductionPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (ICMSPart10.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsAliquot(String icmsAliquot) {
            return (ICMSPart10.Builder) super.withIcmsAliquot(icmsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsValue(String icmsValue) {
            return (ICMSPart10.Builder) super.withIcmsValue(icmsValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withValueMarginAddedStPercent(String valueMarginAddedStPercent) {
            return (ICMSPart10.Builder) super.withValueMarginAddedStPercent(valueMarginAddedStPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcReductionStPercent(String bcReductionStPercent) {
            return (ICMSPart10.Builder) super.withBcReductionStPercent(bcReductionStPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcModalityST(BCModalityST bcModalityST) {
            return (ICMSPart10.Builder) super.withBcModalityST(bcModalityST);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValueST(String bcValueST) {
            return (ICMSPart10.Builder) super.withBcValueST(bcValueST);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsStAliquot(String icmsStAliquot) {
            return (ICMSPart10.Builder) super.withIcmsStAliquot(icmsStAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsStValue(String icmsStValue) {
            return (ICMSPart10.Builder) super.withIcmsStValue(icmsStValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withSelfOperationBCPerc(String selfOperationBCPerc) {
            return (ICMSPart10.Builder) super.withSelfOperationBCPerc(selfOperationBCPerc);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUfST(UF ufST) {
            return (ICMSPart10.Builder) super.withUfST(ufST);
        }

        @Override
        public ICMSPart10 build() {
            return new ICMSPart10(this);
        }

    }

    protected ICMSPart10() {
        super(null, null);
    }

    protected ICMSPart10(ICMSPart10.Builder builder) {
        super(builder, "10");
    }
}
