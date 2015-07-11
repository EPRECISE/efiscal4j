
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.commons.domain.transmission.FiscalDocumentBody;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImplAdapter;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "nfeDadosMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeBody extends FiscalDocumentBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns;

    //@formatter:off
    @XmlElementRefs({
        @XmlElementRef(name = ObjectFactory.ENVI_NFE),
        @XmlElementRef(name = ObjectFactory.CONS_RECI_NFE),
        @XmlElementRef(name = ObjectFactory.CONS_SIT_NFE),
        @XmlElementRef(name = ObjectFactory.CONS_STAT_SERV),
        @XmlElementRef(name = ObjectFactory.ENV_EVENTO),
    })
    @XmlJavaTypeAdapter(TransmissibleBodyImplAdapter.class)
    private final TransmissibleBodyImpl transmissible;
    //@formatter:on

    public static class Builder {

        private String xmlns;

        private TransmissibleBodyImpl transmissible;

        /**
         * 
         * @param xmlns
         * @return
         */
        public Builder withXmlns(String xmlns) {
            this.xmlns = xmlns;
            return this;
        }

        /**
         * 
         * @param transmissible
         * @return
         */
        public Builder withTransmissible(TransmissibleBodyImpl transmissible) {
            this.transmissible = transmissible;
            return this;
        }

        public NFeBody build() {
            final NFeBody entity = new NFeBody(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeBody() {
        this.xmlns = null;
        this.transmissible = null;
    }

    public NFeBody(Builder builder) {
        this.xmlns = builder.xmlns;
        this.transmissible = builder.transmissible;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    @Override
    public TransmissibleBodyImpl getTransmissible() {
        return this.transmissible;
    }

}
