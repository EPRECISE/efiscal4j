
package eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch;

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
import eprecise.efiscal4j.nfse.tc.curitiba.lot.CuritibaLotRps;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;


@XmlRootElement(name = "EnviarLoteRpsEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "lotRps", "signature" })
public class CuritibaLotRpsDispatchAsync extends DefaultAssignable implements TransmissibleBodyImpl, NFSeRequest {

    private static final long serialVersionUID = 1L;
    
    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/curitiba/nfse.xsd";

    private final @NotNull @XmlElement(name = "LoteRps") CuritibaLotRps lotRps;

    private @XmlTransient QName qName = new QName("EnviarLoteRpsEnvio");

    public static class Builder {

        private CuritibaLotRps lotRps;

        /**
         * @param lotRps
         * @return
         */
        public Builder withLotRps(final CuritibaLotRps lotRps) {
            this.lotRps = lotRps;
            return this;
        }

        public CuritibaLotRpsDispatchAsync build() {
            final CuritibaLotRpsDispatchAsync entity = new CuritibaLotRpsDispatchAsync(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        public CuritibaLotRpsDispatchAsync build(final Signer signer) throws Exception {
            CuritibaLotRpsDispatchAsync entity = new CuritibaLotRpsDispatchAsync(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (CuritibaLotRpsDispatchAsync) signer.sign(entity);
            return entity;
        }
    }

    public CuritibaLotRpsDispatchAsync() {
        lotRps = null;
    }

    public CuritibaLotRpsDispatchAsync(final Builder builder) {
        lotRps = builder.lotRps;
    }

    public CuritibaLotRps getLotRps() {
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
        return new FiscalDocumentDeserializer<>(xml, CuritibaLotRpsDispatchAsync.class).deserialize();
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
