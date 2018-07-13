package eprecise.efiscal4j.nfe;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.transmission.NFeTransmissionChannel;
import eprecise.efiscal4j.nfe.transmission.request.NFeNumberDisableDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.response.NFeNumberDisableDispatchResponse;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;
import eprecise.efiscal4j.nfe.version.ProcessedNFeNumberDisableVersion;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FiscalDocumentNumberDisable {
	
	private final FiscalDocumentSupportedVersion version;

	private final FiscalDocumentSerie serie;
	
	private final Emitter emitter;
	
	private final FiscalDocumentModel model;
	
	private final Integer year;
	
	private final Integer beginNumber;
	
	private final Integer endNumber;
	
	private final String justification;
	
	/**
	 * Transmite a inutilização de numeração de série de documentos fiscais
	 * 
	 * @return resultado da transmissão
	 */
	public FiscalDocumentNumberDisable.TransmissionResult transmit() {
		try {
			final NFeNumberDisableDispatchRequest request = version.getEventDispatchNumberDisableClass().getConstructor(this.getClass()).newInstance(this).buildEventDispatchNumberDisable();
			final NFeTransmissionChannel transmissionChannel = version.getTransmissionChannelClass().getConstructor(Certificate.class).newInstance(this.emitter.getCertificate());
			final TypedTransmissionResult<? extends NFeNumberDisableDispatchRequest,? extends NFeNumberDisableDispatchResponse> result = transmissionChannel.transmitNFeNumberDisable(request);
			return FiscalDocumentNumberDisable.TransmissionResult.builder().version(version).result(result).build();
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

		private final EventStatus status;
		
		private final FiscalDocumentNumberDisable document;

	}
	
	@Builder
	@Getter
	public static class TransmissionResult {
		
		private final FiscalDocumentSupportedVersion version;
		
		private final TypedTransmissionResult<? extends NFeNumberDisableDispatchRequest, ? extends NFeNumberDisableDispatchResponse> result;
		
		public ProcessedNFeNumberDisableVersion getProcessedNumberDisableVersion() {
			try {
				return version.getProcessedNumberDisableClass().getConstructor(result.getRequest().getClass(), result.getResponse().getClass()).newInstance(result.getRequest(), result.getResponse());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
		}
		
		public FiscalDocumentNumberDisable.Processed getProcessed(){
			return this.getProcessedNumberDisableVersion().buildProcessedFiscalDocumentDisable();
		}
	}

}
