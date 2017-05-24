
package eprecise.efiscal4j.nfse.tc.elotech.lot;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.ElotechStatementProvisionService;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeNonNegativeInteger;
import eprecise.efiscal4j.signer.domain.SignatureType;


/**
 * Lote de RPS para Nota Fiscal de Serviço Eletrônica
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechLotRps implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "NumeroLote") @NotNull @NFSeNonNegativeInteger @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "QuantidadeRps") @NotNull Integer rpsQuantity;

    private final @XmlElementWrapper(name = "ListaRps") @XmlElement(name = "DeclaracaoPrestacaoServico") @NotNull Collection<ElotechStatementProvisionService> statementProvisionServices;

    public static class Builder {

        private String lotNumber;

        private Integer rpsQuantity;

        private Collection<ElotechStatementProvisionService> statementProvisionServices;

        /**
         * @param lotNumber
         * @return
         */
        public Builder withLotNumber(final String lotNumber) {
            this.lotNumber = lotNumber;
            return this;
        }

        /**
         * @param statementProvisionServices
         * @return
         */
        public Builder withStatementProvisionService(final Collection<ElotechStatementProvisionService> statementProvisionServices) {
            this.statementProvisionServices = statementProvisionServices;
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

        // public LotRps build(final Signer signer) throws Exception {
        // final LotRps entity = new LotRps(this);
        // ValidationBuilder.from(entity).validate().throwIfViolate();
        // entity = (LotRps) signer.sign(entity);
        // return entity;
        // }

        public ElotechLotRps build() throws Exception {
            final ElotechLotRps entity = new ElotechLotRps(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechLotRps() {
        lotNumber = null;
        rpsQuantity = null;
        statementProvisionServices = null;
    }

    public ElotechLotRps(final Builder builder) {
        lotNumber = builder.lotNumber;
        rpsQuantity = builder.rpsQuantity;
        statementProvisionServices = builder.statementProvisionServices;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).considering(ElotechLotRps.getValidationConsideringClasses()).serialize();
    }

    public ElotechLotRps getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, ElotechLotRps.class).considering(ElotechLotRps.getValidationConsideringClasses()).deserialize();
    }

    public Collection<ElotechStatementProvisionService> getStatementProvisionServices() {
        return statementProvisionServices;
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(SignatureType.class);
    }
}
