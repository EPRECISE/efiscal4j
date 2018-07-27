package eprecise.efiscal4j.nfe;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
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
	 * @return resultado da transmissão
	 */
	public FiscalDocumentCCe.TransmissionResult transmit() {
		try {
			final FiscalDocumentSupportedVersion version = this.processedFiscalDocument.getVersion();
			final NFeEventDispatchRequest request = version.getEventDispatchCCeClass().getConstructor(this.getClass()).newInstance(this).buildEventDispatchCCe();
			final NFeTransmissionChannel transmissionChannel = version.getTransmissionChannelClass().getConstructor(Certificate.class).newInstance(this.processedFiscalDocument.getDocument().getEmitter().getCertificate());
			final TypedTransmissionResult<? extends NFeEventDispatchRequest,? extends NFeEventDispatchResponse> result = transmissionChannel.transmitEventReceptionCCe(request, this.processedFiscalDocument.getDocument().getModel());
			return FiscalDocumentCCe.TransmissionResult.builder().version(version).result(result).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Builder
	@Getter
	public static class Processed {
		
		private final String id;
		
		private final Date date;

		private final String protocolNumber;

		private final String details;
		
		private final EventStatus status;
		
		private final FiscalDocumentCCe document;

	}
	
	@Builder
	@Getter
	public static class TransmissionResult {
		
		private final FiscalDocumentSupportedVersion version;
		
		private final TypedTransmissionResult<? extends NFeEventDispatchRequest, ? extends NFeEventDispatchResponse> result;
		
		public ProcessedEventVersion getProcessedEventVersion() {
			try {
				return version.getProcessedEventClass().getConstructor(result.getRequest().getClass(), result.getResponse().getClass()).newInstance(result.getRequest(), result.getResponse());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
		}
		
		public FiscalDocumentCCe.Processed getProcessed(){
			return this.getProcessedEventVersion().buildProcessedFiscalDocumentCCe();
		}
	}

}