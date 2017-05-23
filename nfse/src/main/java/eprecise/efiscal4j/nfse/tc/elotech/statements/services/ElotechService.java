
package eprecise.efiscal4j.nfse.tc.elotech.statements.services;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeNonNegativeInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechService {

    private final @XmlElement(name = "Valores") @NotNull ElotechServiceValues values;

    private final @XmlElement(name = "IssRetido") @NotNull ElotechIssWithheld issWithheld;

    private final @XmlElement(name = "ResponsavelRetencao") ElotechResponsibleRetention responsibleRetention;

    private final @XmlElement(name = "Discriminacao") @NotNull @Size(min = 1, max = 2000) String discrimination;

    private final @XmlElement(name = "CodigoMunicipio") @NotNull @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

    private final @XmlElement(name = "CodigoPais") @Size(min = 4, max = 4) String countryCode;

    private final @XmlElement(name = "ExigibilidadeISS") @NotNull ElotechIssRequirement issRequirement;

    private final @XmlElement(name = "MunicipioIncidencia") @NotNull @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityIncidenceCode;

    private final @XmlElement(name = "NumeroProcesso") @Size(min = 1, max = 30) String processNumber;

    private final @XmlElementWrapper(name = "ListaItensServico") @XmlElement(name = "ItemServico") @NotNull Collection<ElotechServiceItem> serviceItems;

    public static class Builder {

        private ElotechServiceValues values;

        private ElotechIssWithheld issWithheld;

        private ElotechResponsibleRetention responsibleRetention;

        private String discrimination;

        private String cityCode;

        private String countryCode;

        private ElotechIssRequirement issRequirement;

        private String cityIncidenceCode;

        private String processNumber;

        private Collection<ElotechServiceItem> serviceItems;

        /**
         * @param values
         * @return
         */
        public Builder withServiceValues(final ElotechServiceValues values) {
            this.values = values;
            return this;
        }

        /**
         * @param issWithheld
         * @return
         */
        public Builder withIssWithheld(final ElotechIssWithheld issWithheld) {
            this.issWithheld = issWithheld;
            return this;
        }

        /**
         * @param responsibleRetention
         * @return
         */
        public Builder withResponsibleRetention(final ElotechResponsibleRetention responsibleRetention) {
            this.responsibleRetention = responsibleRetention;
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

        /**
         * @param countryCode
         * @return
         */
        public Builder withCountryCode(final String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        /**
         * @param issRequirement
         * @return
         */
        public Builder withIssRequirement(final ElotechIssRequirement issRequirement) {
            this.issRequirement = issRequirement;
            return this;
        }

        /**
         * @param cityIncidenceCode
         * @return
         */
        public Builder withCityIncidenceCode(final String cityIncidenceCode) {
            this.cityIncidenceCode = cityIncidenceCode;
            return this;
        }

        /**
         * @param processNumber
         * @return
         */
        public Builder withProcessNumber(final String processNumber) {
            this.processNumber = processNumber;
            return this;
        }

        /**
         * @param processNumber
         * @return
         */
        public Builder withServiceItems(final Collection<ElotechServiceItem> serviceItems) {
            this.serviceItems = serviceItems;
            return this;
        }

        public ElotechService build() throws Exception {
            final ElotechService entity = new ElotechService(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechService() {
        this.values = null;
        this.issWithheld = null;
        this.responsibleRetention = null;
        this.discrimination = null;
        this.cityCode = null;
        this.countryCode = null;
        this.issRequirement = null;
        this.cityIncidenceCode = null;
        this.processNumber = null;
        this.serviceItems = null;
    }

    public ElotechService(final Builder builder) {
        this.values = builder.values;
        this.issWithheld = builder.issWithheld;
        this.responsibleRetention = builder.responsibleRetention;
        this.discrimination = builder.discrimination;
        this.cityCode = builder.cityCode;
        this.countryCode = builder.countryCode;
        this.issRequirement = builder.issRequirement;
        this.cityIncidenceCode = builder.cityIncidenceCode;
        this.processNumber = builder.processNumber;
        this.serviceItems = builder.serviceItems;
    }

    public ElotechServiceValues getValues() {
        return values;
    }

    public ElotechIssWithheld getIssWithheld() {
        return issWithheld;
    }

    public ElotechResponsibleRetention getResponsibleRetention() {
        return responsibleRetention;
    }

    public String getDiscrimination() {
        return discrimination;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public ElotechIssRequirement getIssRequirement() {
        return issRequirement;
    }

    public String getCityIncidenceCode() {
        return cityIncidenceCode;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public Collection<ElotechServiceItem> getServiceItems() {
        return serviceItems;
    }

}
