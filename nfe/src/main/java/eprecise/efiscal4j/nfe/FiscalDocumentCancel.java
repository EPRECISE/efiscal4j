package eprecise.efiscal4j.nfe;

import java.util.Date;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.transmission.NFeTransmissionChannel;
import eprecise.efiscal4j.nfe.transmission.request.NFeEventDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.response.NFeEventDispatchResponse;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;
import eprecise.efiscal4j.nfe.version.ProcessedEventVersion;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FiscalDocumentCancel {

	private final String justification;

	private final FiscalDocument.Processed processedFiscalDocument;
	
	/**
	 * Transmite o cancelamento do documento fiscal
	 * 
	 * @param versão do documento fiscal
	 * @return documento fiscal processado
	 */
	public FiscalDocumentCancel.Processed transmit() {
		try {
			final FiscalDocumentSupportedVersion version = this.processedFiscalDocument.getVersion();
			final NFeEventDispatchRequest request = version.getEventDispatchCancelClass().getConstructor(this.getClass()).newInstance(this).buildEventDispatchCancel();
			final NFeTransmissionChannel transmissionChannel = version.getTransmissionChannelClass().getConstructor(Certificate.class).newInstance(this.processedFiscalDocument.getDocument().getEmitter().getCertificate());
			final NFeEventDispatchResponse response = transmissionChannel.transmitEventReceptionCancellation(request, this.processedFiscalDocument.getDocument().getModel()).getResponse();
			final ProcessedEventVersion processedEvent = version.getProcessedEventClass().getConstructor(request.getClass(), response.getClass()).newInstance(request, response);
			return processedEvent.buildProcessedFiscalDocumentCancel();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Builder
	@Getter
	public static class Processed {
		
		private final String id;
		
		private final Date date;

		private final String protocol;

		private final String details;
		
		private final EventStatus status;

	}

}
