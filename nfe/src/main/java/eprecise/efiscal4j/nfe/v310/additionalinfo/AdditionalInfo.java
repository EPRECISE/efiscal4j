
package eprecise.efiscal4j.nfe.v310.additionalinfo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Informações adicionais da NF-e
 *
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AdditionalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "infAdFisco") @Size(min = 1, max = 2000) @NFeString String additionalInfoFisco;

    private @XmlElement(name = "infCpl") @Size(min = 1, max = 5000) @NFeString String complementaryInfo;

    private @XmlElement(name = "obsCont") @Size(min = 0, max = 10) List<CustomizedObservation> taxpayerObservations;

    private @XmlElement(name = "obsFisco") @Size(min = 0, max = 10) List<CustomizedObservation> fiscoObservations;

    private @XmlElement(name = "procRef") @Size(min = 0, max = 100) List<ReferencedProcess> referencedProcesses;

    public static class Builder {

        private String additionalInfoFisco;

        private String complementaryInfo;

        private List<CustomizedObservation> taxpayerObservations;

        private List<CustomizedObservation> fiscoObservations;

        private List<ReferencedProcess> referencedProcesses;

        /**
         * Informações adicionais de interesse do Fisco (v2.0)
         * 
         * @param additionalInfoFisco
         * @return
         */
        public Builder withAdditionalInfoFisco(final String additionalInfoFisco) {
            this.additionalInfoFisco = additionalInfoFisco;
            return this;
        }

        /**
         * Informações complementares de interesse do Contribuinte
         * 
         * @param complementaryInfo
         * @return
         */
        public Builder withComplementaryInfo(final String complementaryInfo) {
            this.complementaryInfo = complementaryInfo;
            return this;
        }

        /**
         * List of CustomizedObservation (Campo de uso livre do contribuinte)
         * 
         * @see CustomizedObservation
         * @param taxpayerObservations
         * @return
         */
        public Builder withTaxpayerObservations(final List<CustomizedObservation> taxpayerObservations) {
            this.taxpayerObservations = taxpayerObservations;
            return this;
        }

        /**
         * List of CustomizedObservation (Campo de uso exclusivo do Fisco)
         * 
         * @see CustomizedObservation
         * @param fiscoObservations
         * @return
         */
        public Builder withFiscoObservations(final List<CustomizedObservation> fiscoObservations) {
            this.fiscoObservations = fiscoObservations;
            return this;
        }

        /**
         * List of ReferencedProcess
         * 
         * @see ReferencedProcess
         * @param referencedProcesses
         * @return
         */
        public Builder withReferencedProcesses(final List<ReferencedProcess> referencedProcesses) {
            this.referencedProcesses = referencedProcesses;
            return this;
        }

        public AdditionalInfo build() {
            final AdditionalInfo entity = new AdditionalInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public AdditionalInfo() {

    }

    public AdditionalInfo(final Builder builder) {
        this.additionalInfoFisco = builder.additionalInfoFisco;
        this.complementaryInfo = builder.complementaryInfo;
        this.taxpayerObservations = builder.taxpayerObservations;
        this.fiscoObservations = builder.fiscoObservations;
        this.referencedProcesses = builder.referencedProcesses;
    }

    public String getAdditionalInfoFisco() {
        return this.additionalInfoFisco;
    }

    public String getComplementaryInfo() {
        return this.complementaryInfo;
    }

    public void setComplementaryInfo(final String complementaryInfo) {
        this.complementaryInfo = complementaryInfo;
    }

    public List<CustomizedObservation> getTaxpayerObservations() {
        return this.taxpayerObservations;
    }

    public List<CustomizedObservation> getFiscoObservations() {
        return this.fiscoObservations;
    }

    public List<ReferencedProcess> getReferencedProcesses() {
        return this.referencedProcesses;
    }

}
