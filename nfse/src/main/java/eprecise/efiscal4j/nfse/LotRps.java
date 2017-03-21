
package eprecise.efiscal4j.nfse;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.statements.StatementProvisionService;
import eprecise.efiscal4j.nfse.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.signer.domain.SignatureType;


/**
 * Lote de RPS para Nota Fiscal de Serviço Eletrônica
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = "LoteRps")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "lotNumber", "rpsQuantity", "statementProvisionServices" })
public class LotRps implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/nfse_v1_2.xsd";

    private final @XmlElement(name = "NumeroLote") @NotNull @NFSeNonNegativeInteger @Size(max = 15) String lotNumber;

    private final @XmlElement(name = "QuantidadeRps") @NotNull Integer rpsQuantity;

    private final @XmlElementWrapper(name = "ListaRps") @XmlElement(name = "DeclaracaoPrestacaoServico") @NotNull Collection<StatementProvisionService> statementProvisionServices;

    public static class Builder {

        private String lotNumber;

        private Integer rpsQuantity;

        private Collection<StatementProvisionService> statementProvisionServices;

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
        public Builder withStatementProvisionService(final Collection<StatementProvisionService> statementProvisionServices) {
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

        public LotRps build() throws Exception {
            final LotRps entity = new LotRps(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public LotRps() {
        lotNumber = null;
        rpsQuantity = null;
        statementProvisionServices = null;
    }

    public LotRps(final Builder builder) {
        lotNumber = builder.lotNumber;
        rpsQuantity = builder.rpsQuantity;
        statementProvisionServices = builder.statementProvisionServices;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).considering(LotRps.getValidationConsideringClasses()).serialize();
    }

    public LotRps getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, LotRps.class).considering(LotRps.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(SignatureType.class);
    }
}
