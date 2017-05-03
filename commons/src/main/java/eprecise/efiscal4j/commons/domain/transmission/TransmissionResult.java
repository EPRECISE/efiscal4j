
package eprecise.efiscal4j.commons.domain.transmission;

import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;


public class TransmissionResult {

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
