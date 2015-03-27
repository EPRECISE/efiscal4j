
package eprecise.efiscal4j.cte.sharing;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.cte.TypeEnvironment;
import eprecise.efiscal4j.cte.types.AccessKey;
import eprecise.efiscal4j.cte.types.FormatDate;
import eprecise.efiscal4j.cte.types.TypeCodeSent;
import eprecise.efiscal4j.cte.types.TypeNumberProtocol;


/**
 * 
 * Dados do protocolo de status
 * 
 * @author carlos
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusProtocolInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "tpAmb") TypeEnvironment typeEnvironment;

    private final @XmlElement(name = "verAplic") @Size(min = 1, max = 20) String applicationVersion;

    private final @XmlElement(name = "chCTe") @AccessKey String accessKeyCte;

    private final @XmlElement(name = "dhRecbto") @FormatDate String dateProcessing;

    private final @XmlElement(name = "nProt") @TypeNumberProtocol String numberProtocol;

    private final @XmlElement(name = "digVal") String digestValue;

    private final @XmlElement(name = "cStat") @TypeCodeSent String statusCodeCte;

    private final @XmlElement(name = "xMotivo") @Size(min = 1, max = 255) String literalDescription;

    public static class Builder {

        private TypeEnvironment typeEnvironment;

        private String applicationVersion;

        private String accessKeyCte;

        private String dateProcessing;

        private String numberProtocol;

        private String digestValue;

        private String statusCodeCte;

        private String literalDescription;

        public Builder withTypeEnvironment(TypeEnvironment typeEnvironment) {
            this.typeEnvironment = typeEnvironment;
            return this;
        }

        public Builder withAapplicationVersion(String applicationVersion) {
            this.applicationVersion = applicationVersion;
            return this;
        }

        public Builder withDateProcessing(String dateProcessing) {
            this.dateProcessing = dateProcessing;
            return this;
        }

        public Builder withAccessKeyCte(String accessKeyCte) {
            this.accessKeyCte = accessKeyCte;
            return this;
        }

        public Builder withNumberProtocol(String numberProtocol) {
            this.numberProtocol = numberProtocol;
            return this;
        }

        public Builder withDigestValue(String digestValue) {
            this.digestValue = digestValue;
            return this;
        }

        public Builder withStatusCodeCte(String statusCodeCte) {
            this.statusCodeCte = statusCodeCte;
            return this;
        }

        public Builder withLiteralDescription(String literalDescription) {
            this.literalDescription = literalDescription;
            return this;
        }

        public StatusProtocolInfo build() {
            return new StatusProtocolInfo(this);
        }
    }

    public StatusProtocolInfo() {
        this.typeEnvironment = null;
        this.applicationVersion = null;
        this.accessKeyCte = null;
        this.dateProcessing = null;
        this.numberProtocol = null;
        this.digestValue = null;
        this.statusCodeCte = null;
        this.literalDescription = null;
    }

    public StatusProtocolInfo(Builder builder) {
        this.typeEnvironment = builder.typeEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.accessKeyCte = builder.accessKeyCte;
        this.dateProcessing = builder.dateProcessing;
        this.numberProtocol = builder.numberProtocol;
        this.digestValue = builder.digestValue;
        this.statusCodeCte = builder.statusCodeCte;
        this.literalDescription = builder.literalDescription;
    }

    public TypeEnvironment getTypeEnvironment() {
        return this.typeEnvironment;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getAccessKeyCte() {
        return this.accessKeyCte;
    }

    public String getDateProcessing() {
        return this.dateProcessing;
    }

    public String getNumberProtocol() {
        return this.numberProtocol;
    }

    public String getDigestValue() {
        return this.digestValue;
    }

    public String getStatusCodeCte() {
        return this.statusCodeCte;
    }

    public String getLiteralDescription() {
        return this.literalDescription;
    }
}
