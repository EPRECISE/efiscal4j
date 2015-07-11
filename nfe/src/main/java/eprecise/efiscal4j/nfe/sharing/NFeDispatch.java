
package eprecise.efiscal4j.nfe.sharing;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;


/**
 * Tipo Pedido de Concessão de Autorização da Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.ENVI_NFE)
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDispatch extends TransmissibleBodyImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/enviNFe_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlAttribute(name = "xmlns") final String xmlns;

    private @XmlElement(name = "idLote") @Pattern(regexp = "[0-9]{1,15}") @NotNull final String batchId;

    private @XmlElement(name = "indSinc") @NotNull final SynchronousProcessing synchronousProcessing;

    private @XmlElement(name = "NFe") @Size(min = 1, max = 50) @NotNull @Valid final List<NFe> nFes;

    private @XmlTransient QName qName = new QName(ObjectFactory.ENVI_NFE);

    public static class Builder {

        private String batchId;

        private SynchronousProcessing synchronousProcessing;

        private List<NFe> nFes;

        /**
         * Identificação do lote
         * 
         * @param batchId
         * @return
         */
        public Builder withBatchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        /**
         * @see SynchronousProcessing
         * @param synchronousProcessing
         * @return
         */
        public Builder withSynchronousProcessing(SynchronousProcessing synchronousProcessing) {
            this.synchronousProcessing = synchronousProcessing;
            return this;
        }

        /**
         * List of {@link NFe}
         * 
         * @see NFe
         * @param nFes
         * @return
         */
        public Builder withNFes(List<NFe> nFes) {
            this.nFes = nFes;
            return this;
        }

        public NFeDispatch build() throws SAXException, IOException, ParserConfigurationException {
            final NFeDispatch entity = new NFeDispatch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDispatch() {
        this.xmlns = null;
        this.batchId = null;
        this.synchronousProcessing = null;
        this.nFes = null;
    }

    public NFeDispatch(Builder builder) {
        this.xmlns = (builder.nFes.isEmpty() ? null : builder.nFes.get(0).getXmlns());
        this.batchId = builder.batchId;
        this.synchronousProcessing = builder.synchronousProcessing;
        this.nFes = builder.nFes;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getBatchId() {
        return this.batchId;
    }

    public SynchronousProcessing getSynchronousProcessing() {
        return this.synchronousProcessing;
    }

    public List<NFe> getnFes() {
        return this.nFes;
    }

    @Override
    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

}
