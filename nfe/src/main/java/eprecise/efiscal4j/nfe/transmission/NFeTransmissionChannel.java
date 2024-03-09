
package eprecise.efiscal4j.nfe.transmission;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.nfe.transmission.request.NFeAuthorizationRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeBatchReceiptSearchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeDeliveryDFeDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeEventDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeNumberDisableDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeServiceStatusSearchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeStatusSearchRequest;
import eprecise.efiscal4j.nfe.transmission.response.NFeAuthorizationResponse;
import eprecise.efiscal4j.nfe.transmission.response.NFeBatchReceiptSearchResponse;
import eprecise.efiscal4j.nfe.transmission.response.NFeDeliveryDFeDispatchResponse;
import eprecise.efiscal4j.nfe.transmission.response.NFeEventDispatchResponse;
import eprecise.efiscal4j.nfe.transmission.response.NFeNumberDisableDispatchResponse;
import eprecise.efiscal4j.nfe.transmission.response.NFeServiceStatusSearchResponse;
import eprecise.efiscal4j.nfe.transmission.response.NFeStatusSearchResponse;


public interface NFeTransmissionChannel {

    TypedTransmissionResult<? extends NFeAuthorizationRequest, ? extends NFeAuthorizationResponse> transmitAuthorization(final NFeAuthorizationRequest request)
            throws SAXException, IOException, ParserConfigurationException;

    TypedTransmissionResult<? extends NFeServiceStatusSearchRequest, ? extends NFeServiceStatusSearchResponse> transmitServiceStatusSearch(final NFeServiceStatusSearchRequest serviceStatusSearch,
            final FiscalDocumentModel documentModel);

    TypedTransmissionResult<? extends NFeBatchReceiptSearchRequest, ? extends NFeBatchReceiptSearchResponse> transmitBatchReceiptSearch(final NFeBatchReceiptSearchRequest request, final UF uf);

    TypedTransmissionResult<? extends NFeEventDispatchRequest, ? extends NFeEventDispatchResponse> transmitEventReceptionCancellation(final NFeEventDispatchRequest eventDispatch,
            final FiscalDocumentModel documentModel);

    TypedTransmissionResult<? extends NFeEventDispatchRequest, ? extends NFeEventDispatchResponse> transmitEventReceptionCCe(final NFeEventDispatchRequest eventDispatch,
            final FiscalDocumentModel documentModel);

    TypedTransmissionResult<? extends NFeEventDispatchRequest, ? extends NFeEventDispatchResponse> transmitRecipientManifestationEvent(final NFeEventDispatchRequest eventDispatch);

    TypedTransmissionResult<? extends NFeStatusSearchRequest, ? extends NFeStatusSearchResponse> transmitNFeStatusSearch(final NFeStatusSearchRequest nfeStatusSearch,
            final FiscalDocumentModel documentModel, final UF uf);

    TypedTransmissionResult<? extends NFeNumberDisableDispatchRequest, ? extends NFeNumberDisableDispatchResponse> transmitNFeNumberDisable(final NFeServiceDomain domain, final NFeNumberDisableDispatchRequest nfeNumberDisable);

    TypedTransmissionResult<? extends NFeDeliveryDFeDispatchRequest, ? extends NFeDeliveryDFeDispatchResponse> transmitNFeDeliveryDFe(final NFeDeliveryDFeDispatchRequest deliveryDFeRequest);

}
