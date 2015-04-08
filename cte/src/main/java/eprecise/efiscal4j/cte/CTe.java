
package eprecise.efiscal4j.cte;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "CTe")
@XmlAccessorType(XmlAccessType.FIELD)
public class CTe implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlAttribute(name = "xmlns") @NotNull String xmlns = "http://www.portalfiscal.inf.br/cte";

    private final @XmlElement(name = "infCte") CTeInfo info;

    public static class Builder {

        private CTeInfo info;

        public Builder withInfo(CTeInfo info) {
            this.info = info;
            return this;
        }

        public CTe build() {
            return new CTe(this);
        }
    }

    public CTe() {
        this.info = null;
    }

    public CTe(Builder builder) {
        this.info = builder.info;
    }

    public CTeInfo getInfo() {
        return this.info;
    }

}
