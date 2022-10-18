
package eprecise.efiscal4j.nfse.transmission.goiania;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.goiania.services.dispatch.GoianiaLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;

@XmlRootElement(name = "ArquivoXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaXmlRequest {

	//@formatter:off
    @XmlElementRefs({
        @XmlElementRef(name = "GerarNfseEnvio", type=GoianiaLotRpsDispatchSync.class)
    })
    //@formatter:on
	private final NFSeRequest nfseRequest;

	public static class Builder {

		private NFSeRequest nfseRequest;

		/**
		 *
		 * @param nfseRequest
		 * @return
		 */
		public Builder withNfseRequest(final NFSeRequest nfseRequest) {
			this.nfseRequest = nfseRequest;
			return this;
		}

		public GoianiaXmlRequest build() {
			final GoianiaXmlRequest entity = new GoianiaXmlRequest(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public GoianiaXmlRequest() {
		nfseRequest = null;
	}

	public GoianiaXmlRequest(final Builder builder) {
		nfseRequest = builder.nfseRequest;
	}

	public NFSeRequest getNfseRequest() {
		return nfseRequest;
	}

}
