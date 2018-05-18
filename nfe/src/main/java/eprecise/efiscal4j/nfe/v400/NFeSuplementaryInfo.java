
package eprecise.efiscal4j.nfe.v400;

import java.io.Serializable;
import java.text.ParseException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Informações suplementares Nota Fiscal
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeSuplementaryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "qrCode") @NotNull @Size(min = 100, max = 600) final String qrCode;

    private @XmlElement(name = "urlChave") @NotNull @Size(min = 21, max = 85) final String urlQueryByKey;

    public static class Builder {

        private String qrCode;

        private String urlQueryByKey;

        /**
         * Texto com o QR-Code impresso no DANFE NFC-e
         *
         * @param qrCode
         * @return
         */
        public Builder withQrCode(final String qrCode) {
            this.qrCode = qrCode;
            return this;
        }

        /**
         * Informar a URL da Consulta por chave de acesso da NFC. A mesma URL que deve estar informada no DANFE NFC-e para consulta por chave de acesso
         *
         * @param urlQueryByKey
         * @return
         */
        public Builder withUrlQueryByKey(final String urlQueryByKey) {
            this.urlQueryByKey = urlQueryByKey;
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
        this.urlQueryByKey = null;
    }

    protected NFeSuplementaryInfo(final Builder builder) throws ParseException {
        this.qrCode = builder.qrCode;
        this.urlQueryByKey = builder.urlQueryByKey;
    }

    public String getQrCode() {
        return this.qrCode;
    }

    public String getUrlQueryByKey() {
        return urlQueryByKey;
    }

}
