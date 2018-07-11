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
public class FiscalDocumentCCe {

	private final String correction;

	private final FiscalDocument.Processed processedFiscalDocument;
	
	/**
	 * Transmite a carta de correção do documento fiscal
	 * 
	 * @param versão do documento fiscal
	 * @return documento fiscal processado
	 */
	public FiscalDocumentCCe.Processed transmit() {
		try {
			final FiscalDocumentSupportedVersion version = this.processedFiscalDocument.getVersion();
			final NFeEventDispatchRequest request = version.getEventDispatchCCeClass().getConstructor(this.getClass()).newInstance(this).buildEventDispatchCCe();
			final NFeTransmissionChannel transmissionChannel = version.getTransmissionChannelClass().getConstructor(Certificate.class).newInstance(this.processedFiscalDocument.getDocument().getEmitter().getCertificate());
			final NFeEventDispatchResponse response = transmissionChannel.transmitEventReceptionCCe(request, this.processedFiscalDocument.getDocument().getModel()).getResponse();
			final ProcessedEventVersion processedEvent = version.getProcessedEventClass().getConstructor(request.getClass(), response.getClass()).newInstance(request, response);
			return processedEvent.buildProcessedFiscalDocumentCCe();
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
