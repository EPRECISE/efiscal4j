
package eprecise.efiscal4j.nfe.v310.sharing;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;


/**
 * Tipo Lote de Envio de Evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.ENV_EVENTO)
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDispatch implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/event/envEvento_v1.00.xsd";

    public static final String XSD_CANC = "/eprecise/efiscal4j/nfe/xsd/event/cancellation/envEventoCancNFe_v1.00.xsd";

    public static final String XSD_CCE = "/eprecise/efiscal4j/nfe/xsd/event/cce/envCCe_v1.00.xsd";

    public static final String XSD_RECIP_MANIF = "/eprecise/efiscal4j/nfe/xsd/recipientManifestDfe/envConfRecebto_v1.00.xsd";

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_00;

    private @XmlElement(name = "idLote") @NotNull @Pattern(regexp = "[0-9]{1,15}") final String batchId;

    private @XmlElement(name = "evento") @NotNull @Size(max = 20) @Valid final ArrayList<Event> events;

    private @XmlTransient QName qName = new QName(ObjectFactory.ENV_EVENTO);

    public static class Builder {

        private String batchId;

        private ArrayList<Event> events;

        /**
         * Identificador de controle do Lote de envio do Evento. <br>
         * Número seqüencial autoincremental único para identificação do Lote. <br>
         * A responsabilidade de gerar e controlar é exclusiva do autor do evento. <br>
         * O Web Service não faz qualquer uso deste identificador.
         * 
         * @param batchId
         * @return
         */
        public Builder withBatchId(final String batchId) {
            this.batchId = batchId;
            return this;
        }

        /**
         * List of {@link Event}
         * 
         * @see Event
         * @param events
         * @return
         */
        public Builder withEvents(final ArrayList<Event> events) {
            this.events = events;
            return this;
        }

        public EventDispatch build() {
            final EventDispatch entity = new EventDispatch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public EventDispatch() {
        this.batchId = null;
        this.events = null;
    }

    public EventDispatch(final Builder builder) {
        this.batchId = builder.batchId;
        this.events = builder.events;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getBatchId() {
        return this.batchId;
    }

    public ArrayList<Event> getEvents() {
        return this.events;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }
}
