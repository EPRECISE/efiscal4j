
package eprecise.efiscal4j.nfse.domain.person.address;

import java.io.Serializable;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String street;

    private final String district;

    private final String number;

    private final String details;

    private final NFSeCity city;

    private final String zipCode;

    public static class Builder {

        private String street;

        private String district;

        private String number;

        private String details;

        private NFSeCity city;

        private String zipCode;

        public Builder withStreet(final String street) {
            this.street = street;
            return this;
        }

        public Builder withDistrict(final String district) {
            this.district = district;
            return this;
        }

        public Builder withNumber(final String number) {
            this.number = number;
            return this;
        }

        public Builder withDetails(final String details) {
            this.details = details;
            return this;
        }

        public Builder withCity(final NFSeCity city) {
            this.city = city;
            return this;
        }

        public Builder withZipCode(final String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public NFSeAddress build() {
            final NFSeAddress entity = new NFSeAddress(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeAddress() {
        street = null;
        district = null;
        number = null;
        details = null;
        city = null;
        zipCode = null;
    }

    public NFSeAddress(final Builder builder) {
        street = builder.street;
        district = builder.district;
        number = builder.number;
        details = builder.details;
        city = builder.city;
        zipCode = builder.zipCode;
    }

    public String getDistrict() {
        return district;
    }

    public String getNumber() {
        return number;
    }

    public String getDetails() {
        return details;
    }

    public NFSeCity getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

}
