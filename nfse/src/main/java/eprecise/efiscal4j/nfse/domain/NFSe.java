
package eprecise.efiscal4j.nfse.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.serie.NFSeSerie;
import eprecise.efiscal4j.nfse.domain.service.NFSeService;
import eprecise.efiscal4j.nfse.domain.service.emitter.NFSeServiceEmitter;
import eprecise.efiscal4j.nfse.domain.service.intermediary.NFSeServiceIntermediary;
import eprecise.efiscal4j.nfse.domain.service.taker.NFSeServiceTaker;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeIssHeld;
import eprecise.efiscal4j.nfse.domain.specialTaxationRegime.NFSeSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeSpecificData;
import eprecise.efiscal4j.nfse.domain.tax.NFSeTax;


public class NFSe implements Serializable {

    private static final long serialVersionUID = 1L;

    private final NFSeSerie serie;

    private final Date emission;

    private final NFSeServiceEmitter emitter;

    private final NFSeServiceTaker taker;

    private final NFSeServiceIntermediary intermediary;

    private final NFSeIssHeld issHeld;

    private final NFSeService service;

    private final NFSeTax tax;

    private final NFSeSpecialTaxationRegime specialTaxationRegime;

    private final NFSeSpecificData specificData;

    private final BigDecimal netValue;

    public static class Builder {

        private NFSeSerie serie;

        private Date emission;

        private NFSeServiceEmitter emitter;

        private NFSeServiceTaker taker;

        private NFSeServiceIntermediary intermediary;

        private NFSeIssHeld issHeld;

        private NFSeService service;

        private NFSeTax tax;

        private NFSeSpecialTaxationRegime specialTaxationRegime;

        private NFSeSpecificData specificData;

        private BigDecimal netValue;

        public Builder withSerie(final NFSeSerie serie) {
            this.serie = serie;
            return this;
        }

        public Builder withEmissionDate(final Date emission) {
            this.emission = emission;
            return this;
        }

        public Builder withEmitter(final NFSeServiceEmitter emitter) {
            this.emitter = emitter;
            return this;
        }

        public Builder withTaker(final NFSeServiceTaker taker) {
            this.taker = taker;
            return this;
        }

        public Builder withIntermediary(final NFSeServiceIntermediary intermediary) {
            this.intermediary = intermediary;
            return this;
        }

        public Builder withIssHeld(final NFSeIssHeld issHeld) {
            this.issHeld = issHeld;
            return this;
        }

        public Builder withService(final NFSeService service) {
            this.service = service;
            return this;
        }

        public Builder withTax(final NFSeTax tax) {
            this.tax = tax;
            return this;
        }

        public Builder withSpecialTaxationRegime(final NFSeSpecialTaxationRegime specialTaxationRegime) {
            this.specialTaxationRegime = specialTaxationRegime;
            return this;
        }

        public Builder withSpecificData(final NFSeSpecificData specificData) {
            this.specificData = specificData;
            return this;
        }

        public Builder withNetValue(final BigDecimal netValue) {
            this.netValue = netValue;
            return this;
        }

        public NFSe build() {
            final NFSe entity = new NFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSe() {
        serie = null;
        emission = null;
        emitter = null;
        taker = null;
        intermediary = null;
        issHeld = null;
        service = null;
        tax = null;
        specialTaxationRegime = null;
        specificData = null;
        netValue = null;
    }

    public NFSe(final Builder builder) {
        serie = builder.serie;
        emission = builder.emission;
        emitter = builder.emitter;
        taker = builder.taker;
        intermediary = builder.intermediary;
        issHeld = builder.issHeld;
        service = builder.service;
        tax = builder.tax;
        specialTaxationRegime = builder.specialTaxationRegime;
        specificData = builder.specificData;
        netValue = builder.netValue;
    }

    public NFSeSerie getSerie() {
        return serie;
    }

    public Date getEmission() {
        return emission;
    }

    public NFSeServiceEmitter getEmitter() {
        return emitter;
    }

    public NFSeServiceTaker getTaker() {
        return taker;
    }

    public NFSeServiceIntermediary getIntermediary() {
        return intermediary;
    }

    public NFSeIssHeld getIssHeld() {
        return issHeld;
    }

    public NFSeService getService() {
        return service;
    }

    public NFSeTax getTax() {
        return tax;
    }

    public NFSeSpecialTaxationRegime getSpecialTaxationRegime() {
        return specialTaxationRegime;
    }

    public NFSeSpecificData getSpecificData() {
        return specificData;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

}
