
package eprecise.efiscal4j.nfse.domain;

import java.io.Serializable;
import java.util.Date;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeCity;
import eprecise.efiscal4j.nfse.domain.service.NFSeService;
import eprecise.efiscal4j.nfse.domain.service.emitter.NFSeServiceEmitter;
import eprecise.efiscal4j.nfse.domain.service.intermediary.NFSeServiceIntermediary;
import eprecise.efiscal4j.nfse.domain.service.taker.NFSeServiceTaker;
import eprecise.efiscal4j.nfse.domain.service.withheld.NFSeIssHeld;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeSpecificData;
import eprecise.efiscal4j.nfse.domain.tax.NFSeTax;


public class NFSe implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String lotNumber;

    private final String rpsNumber;

    private final Date emission;

    private final NFSeServiceEmitter emitter;

    private final NFSeServiceTaker taker;

    private final NFSeServiceIntermediary intermediary;

    private final NFSeIssHeld issHeld;

    private final NFSeCity cityService;

    private final NFSeService service;

    private final NFSeTax tax;

    private final NFSeSpecificData specificData;

    public static class Builder {

        private String lotNumber;

        private String rpsNumber;

        private Date emission;

        private NFSeServiceEmitter emitter;

        private NFSeServiceTaker taker;

        private NFSeServiceIntermediary intermediary;

        private NFSeIssHeld issHeld;

        private NFSeCity cityService;

        private NFSeService service;

        private NFSeTax tax;

        private NFSeSpecificData specificData;

        public Builder withLotNumber(final String lotNumber) {
            this.lotNumber = lotNumber;
            return this;
        }

        public Builder withRpsNumber(final String rpsNumber) {
            this.rpsNumber = rpsNumber;
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

        public Builder withCityService(final NFSeCity cityService) {
            this.cityService = cityService;
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

        public Builder withSpecificData(final NFSeSpecificData specificData) {
            this.specificData = specificData;
            return this;
        }

        public NFSe build() {
            final NFSe entity = new NFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSe() {
        lotNumber = null;
        rpsNumber = null;
        emission = null;
        emitter = null;
        taker = null;
        intermediary = null;
        issHeld = null;
        cityService = null;
        service = null;
        tax = null;
        specificData = null;
    }

    public NFSe(final Builder builder) {
        super();
        lotNumber = builder.lotNumber;
        rpsNumber = builder.rpsNumber;
        emission = builder.emission;
        emitter = builder.emitter;
        taker = builder.taker;
        intermediary = builder.intermediary;
        issHeld = builder.issHeld;
        cityService = builder.cityService;
        service = builder.service;
        tax = builder.tax;
        specificData = builder.specificData;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getRpsNumber() {
        return rpsNumber;
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

    public NFSeCity getCityService() {
        return cityService;
    }

    public NFSeService getService() {
        return service;
    }

    public NFSeTax getTax() {
        return tax;
    }

    public NFSeSpecificData getSpecificData() {
        return specificData;
    }

}
