
package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.govbr.lot.GovbrLotRps;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;


@XmlRootElement(name = "EnviarLoteRpsEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "lotRps", "signature" })
public class GovbrLotRpsDispatchAsync extends DefaultAssignable implements TransmissibleBodyImpl, NFSeRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/servico_enviar_lote_rps_envio.xsd";

    private final @NotNull @XmlElement(name = "LoteRps") GovbrLotRps lotRps;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsEnvio");

    public static class Builder {

        private GovbrLotRps lotRps;

        /**
         * @param lotRps
         * @return
         */
        public Builder withLotRps(final GovbrLotRps lotRps) {
            this.lotRps = lotRps;
            return this;
        }

        public GovbrLotRpsDispatchAsync build() {
            final GovbrLotRpsDispatchAsync entity = new GovbrLotRpsDispatchAsync(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        public GovbrLotRpsDispatchAsync build(final Signer signer) throws Exception {
            GovbrLotRpsDispatchAsync entity = new GovbrLotRpsDispatchAsync(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (GovbrLotRpsDispatchAsync) signer.sign(entity);
            return entity;
        }
    }

    public GovbrLotRpsDispatchAsync() {
        lotRps = null;
    }

    public GovbrLotRpsDispatchAsync(final Builder builder) {
        lotRps = builder.lotRps;
    }

    public GovbrLotRps getLotRps() {
        return lotRps;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return qName;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, GovbrLotRpsDispatchAsync.class).deserialize();
    }

    @Override
    public String getRootTagName() {
        return "EnviarLoteRpsEnvio";
    }

    @Override
    public String getAssignableTagName() {
        return "LoteRps";
    }

    @Override
    public String getIdAttributeTagName() {
        return "id";
    }

}
