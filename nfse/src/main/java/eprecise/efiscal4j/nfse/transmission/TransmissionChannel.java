
package eprecise.efiscal4j.nfse.transmission;

import java.util.Optional;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatchResponse;
import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;
import eprecise.efiscal4j.nfse.signer.NFSeSigner;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPBody;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPHeader;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Fernando C Glizt
 *
 */
public class TransmissionChannel {

    private final Transmissor transmissor;

    private Signer signer;

    public TransmissionChannel() {
        transmissor = null;
    }

    public TransmissionChannel(final NFSeSigner signer) {
        transmissor = new Transmissor(signer.getKeyCertificate());
        this.signer = signer;
    }

    public TypedTransmissionResult<SOAPEnvelope, LotRpsDispatchResponse> transmitAuthorization(final LotRpsDispatch lotRpsDispatch) throws Exception {

        final String cityCode = lotRpsDispatch.getLotRps().getStatementProvisionServices().stream().findAny().orElseThrow(IllegalStateException::new).getInfo().getServiceProvider().getAddress()
                .getCityCode();

        final SOAPEnvelope soapEnvelope = new SOAPEnvelope.Builder().withSoapHeader(new SOAPHeader.Builder().build()).withSoapBody(new SOAPBody.Builder().withTransmissibleBody(lotRpsDispatch).build())
                .build(signer);
        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).withNamespacePrefixMapper(new NFSeNamespacesPrefixMapper()).serialize();

        String responseXml = transmissor.transmit(requestXml, NFSeService.getUrl(cityCode));

        responseXml = responseXml.substring(responseXml.indexOf("<SOAP-ENV:Body"), responseXml.lastIndexOf("</SOAP-ENV:Body>"));

        System.out.println(responseXml);

        return new TypedTransmissionResult<>(SOAPEnvelope.class, LotRpsDispatchResponse.class, requestXml, responseXml);

    }

    public static class TransmissionResult {

        private final String requestXml;

        private final String responseXml;

        public TransmissionResult(final String requestXml, final String responseXml) {
            this.requestXml = requestXml;
            this.responseXml = responseXml;
        }

        public String getRequestXml() {
            return requestXml;
        }

        public String getResponseXml() {
            return responseXml;
        }

        public <T> T getRequest(final Class<T> type) {
            return new FiscalDocumentDeserializer<>(requestXml, type).deserialize();
        }

        public <T> T getRespose(final Class<T> type) {
            return new FiscalDocumentDeserializer<>(responseXml, type).deserialize();
        }
    }

    /**
     *
     * @author Clécius J. Martinkoski
     *
     * @param <RQ>
     * @param <RP>
     */
    public static class TypedTransmissionResult<RQ, RP> extends TransmissionResult {

        private final Class<RQ> requestType;

        private final Class<RP> responseType;

        private Optional<RQ> request = Optional.empty();

        private Optional<RP> response = Optional.empty();

        public TypedTransmissionResult(final Class<RQ> requestType, final Class<RP> responseType, final String requestXml, final String responseXml) {
            super(requestXml, responseXml);
            this.requestType = requestType;
            this.responseType = responseType;

        }

        public RQ getRequest() {
            return this.request.orElseGet(() -> {
                this.request = Optional.of(this.getRequest(this.requestType));
                return this.request.get();
            });
        }

        public RP getResponse() {
            return this.response.orElseGet(() -> {
                this.response = Optional.of(this.getRespose(this.responseType));
                return this.response.get();
            });
        }

    }

}
