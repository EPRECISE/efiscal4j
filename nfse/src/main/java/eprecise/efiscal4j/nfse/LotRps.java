
package eprecise.efiscal4j.nfse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.signer.domain.SignatureType;


/**
 * Lote de RPS para Nota Fiscal de Serviço Eletrônica
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = "LoteRps")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "NumeroLote" })
public class LotRps implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "NumeroLote") @NotNull @Max(15) final String number;

    public static class Builder {

        private String number;

        /**
         * @param number
         * @return
         */
        public Builder withNFeInfo(final String number) {
            this.number = number;
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
        this.number = null;
    }

    public LotRps(final Builder builder) {
        this.number = builder.number;
    }

    public String getNumber() {
        return number;
    }

    public String getAsXml() {
        return new FiscalDocumentSerializer<LotRps>(this).considering(LotRps.getValidationConsideringClasses()).serialize();
    }

    public LotRps getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<LotRps>(xml, LotRps.class).considering(LotRps.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(SignatureType.class);
    }
}
