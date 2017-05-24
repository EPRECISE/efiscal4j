
package eprecise.efiscal4j.nfse.tc.govbr.lot.rps.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeNonNegativeInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrService {

    private final @XmlElement(name = "Valores") @NotNull GovbrValues values;

    private final @XmlElement(name = "ItemListaServico") @NotNull @Size(min = 1, max = 5) String itemServiceList;

    private final @XmlElement(name = "CodigoCnae") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cnaeCode;

    private final @XmlElement(name = "CodigoTributacaoMunicipio") @Size(min = 1, max = 20) String municipalTaxCode;

    private final @XmlElement(name = "Discriminacao") @NotNull @Size(min = 1, max = 2000) String discrimination;

    private final @XmlElement(name = "CodigoMunicipio") @NotNull @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

    public static class Builder {

        private GovbrValues values;

        private String itemServiceList;

        private String cnaeCode;

        private String municipalTaxCode;

        private String discrimination;

        private String cityCode;

        /**
         * @param values
         * @return
         */
        public Builder withServiceValues(final GovbrValues values) {
            this.values = values;
            return this;
        }

        /**
         * @param itemServiceList
         * @return
         */
        public Builder withItemServiceList(final String itemServiceList) {
            this.itemServiceList = itemServiceList;
            return this;
        }

        /**
         * @param cnaeCode
         * @return
         */
        public Builder withCnaeCode(final String cnaeCode) {
            this.cnaeCode = cnaeCode;
            return this;
        }

        /**
         * @param municipalTaxCode
         * @return
         */
        public Builder withMunicipalTaxCode(final String municipalTaxCode) {
            this.municipalTaxCode = municipalTaxCode;
            return this;
        }

        /**
         * @param discrimination
         * @return
         */
        public Builder withDiscrimination(final String discrimination) {
            this.discrimination = discrimination;
            return this;
        }

        /**
         * @param cityCode
         * @return
         */
        public Builder withCityCode(final String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        public GovbrService build() throws Exception {
            final GovbrService entity = new GovbrService(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrService() {
        values = null;
        itemServiceList = null;
        cnaeCode = null;
        municipalTaxCode = null;
        discrimination = null;
        cityCode = null;
    }

    public GovbrService(final Builder builder) {
        values = builder.values;
        itemServiceList = builder.itemServiceList;
        cnaeCode = builder.cnaeCode;
        municipalTaxCode = builder.municipalTaxCode;
        discrimination = builder.discrimination;
        cityCode = builder.cityCode;
    }

    public GovbrValues getValues() {
        return values;
    }

    public String getItemServiceList() {
        return itemServiceList;
    }

    public String getCnaeCode() {
        return cnaeCode;
    }

    public String getMunicipalTaxCode() {
        return municipalTaxCode;
    }

    public String getDiscrimination() {
        return discrimination;
    }

    public String getCityCode() {
        return cityCode;
    }

}
