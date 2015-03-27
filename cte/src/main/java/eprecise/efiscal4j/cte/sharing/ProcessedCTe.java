
package eprecise.efiscal4j.cte.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.cte.CTe;


/**
 * @author carlos
 * 
 * 
 */
@XmlRootElement(name = "cteProc")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessedCTe implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "CTe") CTe cte;

    private final @XmlElement(name = "protCTe") StatusProtocol typeStatusProtocol;

    private final @XmlAttribute(name = "versao") @NotNull String version;

    public static class Builder {

        private CTe cte;

        private StatusProtocol typeStatusProtocol;

        private String version = "2.00";

        public Builder withCTe(CTe cte) {
            this.cte = cte;
            return this;
        }

        public Builder withTypeStatusProtocol(StatusProtocol typeStatusProtocol) {
            this.typeStatusProtocol = typeStatusProtocol;
            return this;
        }

        public Builder withVersion(String version) {
            this.version = version;
            return this;
        }

        public ProcessedCTe build() {
            return new ProcessedCTe(this);
        }
    }

    public ProcessedCTe() {
        this.cte = null;
        this.typeStatusProtocol = null;
        this.version = null;
    }

    public ProcessedCTe(Builder builder) {
        this.cte = builder.cte;
        this.typeStatusProtocol = builder.typeStatusProtocol;
        this.version = builder.version;
    }

    public CTe getCte() {
        return this.cte;
    }

    public StatusProtocol getTypeStatusProtocol() {
        return this.typeStatusProtocol;
    }

    public String getVersion() {
        return this.version;
    }
}
