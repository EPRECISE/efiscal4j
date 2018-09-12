
package eprecise.efiscal4j.nfse.tc.govbr.v100.lot;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.govbr.v100.lot.rps.GovbrRps;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;


/**
 * Lote de RPS para Nota Fiscal de Serviço Eletrônica
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRps implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "id") final String id = UUID.randomUUID().toString().replaceAll("-", "");

    private final @XmlElement(name = "NumeroLote") @NotNull @NFSeNonNegativeInteger @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "Cnpj") @NotNull @CNPJ(formatted = false) @Size(max = 14) String cnpj;

    private final @NotNull @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 15) String municipalRegistration;

    private final @XmlElement(name = "QuantidadeRps") @NotNull Integer rpsQuantity;

    private final @XmlElementWrapper(name = "ListaRps") @XmlElement(name = "Rps") @NotNull Collection<GovbrRps> rpsList;

    public static class Builder {

        private String lotNumber;

        private String cnpj;

        private String municipalRegistration;

        private Integer rpsQuantity;

        private Collection<GovbrRps> rpsList;

        /**
         * @param lotNumber
         * @return
         */
        public Builder withLotNumber(final String lotNumber) {
            this.lotNumber = lotNumber;
            return this;
        }

        /**
         * @param cnpj
         * @return
         */
        public Builder withCnpj(final String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        /**
         * @param municipalRegistration
         * @return
         */
        public Builder withMunicipalRegistration(final String municipalRegistration) {
            this.municipalRegistration = municipalRegistration;
            return this;
        }

        /**
         * @param rpsList
         * @return
         */
        public Builder withRpsList(final Collection<GovbrRps> rpsList) {
            this.rpsList = rpsList;
            return this;
        }

        /**
         * @param rpsQuantity
         * @return
         */
        public Builder withRpsQuantity(final Integer rpsQuantity) {
            this.rpsQuantity = rpsQuantity;
            return this;
        }

        public GovbrLotRps build() {
            final GovbrLotRps entity = new GovbrLotRps(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrLotRps() {
        lotNumber = null;
        rpsQuantity = null;
        cnpj = null;
        municipalRegistration = null;
        rpsList = null;
    }

    public GovbrLotRps(final Builder builder) {
        lotNumber = builder.lotNumber;
        cnpj = builder.cnpj;
        municipalRegistration = builder.municipalRegistration;
        rpsQuantity = builder.rpsQuantity;
        rpsList = builder.rpsList;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getMunicipalRegistration() {
        return municipalRegistration;
    }

    public Collection<GovbrRps> getRpsList() {
        return rpsList;
    }

}
