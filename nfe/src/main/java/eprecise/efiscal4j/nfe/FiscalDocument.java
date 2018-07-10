
package eprecise.efiscal4j.nfe;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.emissionDate.EmissionDate;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.transmission.NFeTransmissionChannel;
import eprecise.efiscal4j.nfe.transmission.request.NFeAuthorizationRequest;
import eprecise.efiscal4j.nfe.transmission.response.NFeAuthorizationResponse;
import eprecise.efiscal4j.nfe.transport.Transport;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;
import eprecise.efiscal4j.nfe.version.ProcessedNFeVersion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Base da Nota Fiscal Eletrônica, utilizada tanto para NF-e quanto para NFC-e
 *
 * @author Fernando Glizt
 *
 */
@AllArgsConstructor
@Getter
public abstract class FiscalDocument {

	/**
	 * @see FiscalDocumentSerie
	 * @param serie
	 */
	private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.serie.isNotNull}") @Valid final FiscalDocumentSerie serie;

	/**
	 * Número do Documento Fiscal
	 * 
	 * @param number
	 */
	private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isNotNull}") @Min(value = 1, message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isMinSize}") @Max(value = 999999999, message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isMaxSize}") final Integer number;

	/**
	 * @see EmissionDate
	 * @param emission
	 */
	private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.emission.isNotNull}") @Valid final EmissionDate emission;

	/**
	 * @see Emitter
	 * @param emitter
	 */
	private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.emitter.isNotNull}") @Valid final Emitter emitter;

	/**
	 * @see Item
	 * @param items
	 */
	private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.items.isNotNull}") @Size(min = 1, max = 990, message = "{eprecise.efiscal4j.nfe.fiscalDocument.items.isSize}") @Valid final Collection<Item> items;

	/**
	 * @see Charging
	 * @param charging
	 */
	private final @Valid Charging charging;

	/**
	 * @see Payment
	 * @param payment
	 */
	private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.payment.isNotNull}") @Valid final Payment payment;

	/**
	 * @see Transport
	 * @param transport
	 */
	private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.transport.isNotNull}") @Valid final Transport transport;

	/**
	 * Informações complementares de interesse do Contribuinte
	 * 
	 * @param details
	 */
	private @Size(min = 1, max = 5000, message = "{eprecise.efiscal4j.nfe.fiscalDocument.details.isSize}") final String details;

	
	public abstract FiscalDocumentModel getModel();
	
	/**
	 * Transmite o documento fiscal
	 * 
	 * @param versão do documento fiscal
	 * @return documento fiscal processado
	 */
	public FiscalDocument.Processed transmit(FiscalDocumentSupportedVersion version) {
		try {
			final NFeAuthorizationRequest request = version.getNfeDispatchAdapterClass().getConstructor(this.getClass()).newInstance(this).buildNFeDispatch();
			final NFeTransmissionChannel transmissionChannel = version.getTransmissionChannelClass().getConstructor(Certificate.class).newInstance(this.emitter.getCertificate());
			final NFeAuthorizationResponse response = transmissionChannel.transmitAuthorization(request).getResponse();
			final ProcessedNFeVersion processedNFe = version.getProcessedNFeClass().getConstructor(request.getClass(), response.getClass()).newInstance(request, response);
			return processedNFe.buildProcessedFiscalDocument();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | SAXException | IOException | ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	

	@Builder
	@Getter
	public static class Processed {

		private final String id;

		private final FiscalDocumentSupportedVersion version;

		private final String applicationVersion;

		private final String accessKey;

		private final Date processing;

		private final String protocolNumber;

		private final String digestValue;

		private final EventStatus status;

		private final FiscalDocument document;

		public static class ProcessedBuilder {

			public Processed buildFromXml(final String xml) {

				for (FiscalDocumentSupportedVersion v : FiscalDocumentSupportedVersion.values()) {
					try {
						final String xsdPath = (String) v.getProcessedNFeClass().getDeclaredField("XSD").get(null);
						final FiscalDocumentValidator validator = new FiscalDocumentValidator(
								this.getClass().getResource(xsdPath));
						if (validator.validate(xml).isValid()) {
							final ProcessedNFeVersion processedNFeVersion = new FiscalDocumentDeserializer<>(xml,
									v.getProcessedNFeClass()).deserialize();
							return processedNFeVersion.buildProcessedFiscalDocument();
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}

				return null;
			}

		}
		
		public Object cancel(final String justification) {
			
			return null;
		}

	}
}
