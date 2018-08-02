
package eprecise.efiscal4j.nfe;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.nfe.charging.Charging;
import eprecise.efiscal4j.nfe.emissionDate.EmissionDate;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.payment.Payment;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.total.FiscalDocumentTotal;
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

    private final String code;

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
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isNotNull}") @Min(value = 1, message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isMinSize}") @Max(
            value = 999999999, message = "{eprecise.efiscal4j.nfe.fiscalDocument.number.isMaxSize}") final Integer number;

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
    private @NotNull(message = "{eprecise.efiscal4j.nfe.fiscalDocument.items.isNotNull}") @Size(
            min = 1, max = 990, message = "{eprecise.efiscal4j.nfe.fiscalDocument.items.isSize}") @Valid final List<Item> items;

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

    public abstract boolean isEndConsumer();

    public FiscalDocumentTotal getTotal() {
        return new FiscalDocumentTotal(() -> this.items);
    }

    public Integer getItemOrder(final Item item) {
        return Optional.ofNullable(this.items).map(itemList -> itemList.indexOf(item)).map(index -> index + 1).orElse(null);
    }

    /**
     * Transmite o documento fiscal
     * 
     * @param versão
     *            do documento fiscal
     * @return documento fiscal processado
     */
    public FiscalDocument.TransmissionResult transmit(final FiscalDocumentSupportedVersion version, final Certificate certificate) {
        // @formatter:off
		try {
			final NFeAuthorizationRequest request = version.getNfeDispatchAdapterClass().getConstructor(FiscalDocument.class, Certificate.class).newInstance(this, certificate).buildNFeDispatch();
			final NFeTransmissionChannel transmissionChannel = version.getTransmissionChannelClass().getConstructor(Certificate.class).newInstance(certificate);
			final TypedTransmissionResult<? extends NFeAuthorizationRequest, ? extends NFeAuthorizationResponse> result = transmissionChannel.transmitAuthorization(request);
			return FiscalDocument.TransmissionResult.builder().version(version).result(result).build();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | SAXException | IOException | ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
		// @formatter:on
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

        private final ProcessedNFeVersion processedVersion;

        public static class ProcessedBuilder {

            public Processed buildFromXml(final String xml) {
                // @formatter:off
                final StringBuilder errors = new StringBuilder();
				for (final FiscalDocumentSupportedVersion v : FiscalDocumentSupportedVersion.values()) {
					try {
						final String xsdPath = (String) v.getProcessedNFeClass().getDeclaredField("XSD").get(null);
						final FiscalDocumentValidator validator = new FiscalDocumentValidator(this.getClass().getResource(xsdPath));
						final ValidationResult validate = validator.validate(xml);
                        if (validate.isValid()) {
							final ProcessedNFeVersion processedNFeVersion = new FiscalDocumentDeserializer<>(xml, v.getProcessedNFeClass()).notStoppingOnError().deserialize();
							return processedNFeVersion.buildProcessedFiscalDocument();
						}
                        errors.append("\n").append(validate.getError());
					} catch (final Exception e) {
						throw new RuntimeException(e);
					}
				}

				throw new IllegalStateException(errors.toString());
				// @formatter:on
            }

            public Processed buildFromXml(final InputStream xml) {
                try {
                    return this.buildFromXml(IOUtils.toString(xml, StandardCharsets.UTF_8));
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public String getXml() {
            return new FiscalDocumentSerializer<>(this.processedVersion).serialize();
        }

    }

    @Builder
    @Getter
    public static class TransmissionResult {

        private final FiscalDocumentSupportedVersion version;

        private final TypedTransmissionResult<? extends NFeAuthorizationRequest, ? extends NFeAuthorizationResponse> result;

        public ProcessedNFeVersion getProcessedNFeVersion() {
            try {
                return this.version.getProcessedNFeClass().getConstructor(this.result.getRequest().getClass(), this.result.getResponse().getClass()).newInstance(this.result.getRequest(),
                        this.result.getResponse());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }

        public FiscalDocument.Processed getProcessed() {
            return this.getProcessedNFeVersion().buildProcessedFiscalDocument();
        }

        public EventStatus getStatus() {
            return Optional.ofNullable(this.result).map(r -> r.getResponse()).map(rp -> rp.getStatus()).orElse(null);
        }

    }
}
