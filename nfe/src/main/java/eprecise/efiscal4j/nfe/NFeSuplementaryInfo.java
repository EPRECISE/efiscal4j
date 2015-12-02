
package eprecise.efiscal4j.nfe;

import java.io.Serializable;
import java.text.ParseException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFCeQrCode;


/**
 * Informações suplementares Nota Fiscal
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeSuplementaryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "qrCode") @NFCeQrCode @NotNull @Size(min = 100, max = 600) final String qrCode;

    public static class Builder {

        private String qrCode;

        public Builder withQrCode(final String qrCode) {
            this.qrCode = qrCode;
            return this;
        }

        public NFeSuplementaryInfo build() throws ParseException {
            final NFeSuplementaryInfo entity = new NFeSuplementaryInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    protected NFeSuplementaryInfo() {
        this.qrCode = null;
    }

    protected NFeSuplementaryInfo(final Builder builder) throws ParseException {
        this.qrCode = builder.qrCode;
    }

    public String getQrCode() {
        return qrCode;
    }

}
