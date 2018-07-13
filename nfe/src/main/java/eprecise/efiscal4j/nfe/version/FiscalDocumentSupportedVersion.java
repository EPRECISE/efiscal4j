
package eprecise.efiscal4j.nfe.version;

import eprecise.efiscal4j.nfe.transmission.NFeTransmissionChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author Fernando Glizt
 *
 */

@AllArgsConstructor
@Getter
public enum FiscalDocumentSupportedVersion {
	
	
	VERSION_4_00("4.00",
			eprecise.efiscal4j.nfe.v400.transmission.TransmissionChannel.class,
			eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument.DispatchFromFiscalDocumentAdapter.class,
			eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFe.class,
			eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument.ProcessedFiscalDocumentAdapter.class,
			eprecise.efiscal4j.nfe.v400.sharing.EventProtocol.class,
			eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument.EventDispatchCancelAdapter.class,
			eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument.EventDispatchCCeAdapter.class,
			eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument.EventDispatchNumberDisableAdapter.class,
			eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFeNumberDisable.class), 
	VERSION_3_10("3.10",
			eprecise.efiscal4j.nfe.v310.transmission.TransmissionChannel.class,
			eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument.DispatchFromFiscalDocumentAdapter.class,
			eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFe.class,
			eprecise.efiscal4j.nfe.v310.sharing.adapters.processedFiscalDocument.ProcessedFiscalDocumentAdapter.class,
			eprecise.efiscal4j.nfe.v310.sharing.EventProtocol.class,
			eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument.EventDispatchCancelAdapter.class,
			eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument.EventDispatchCCeAdapter.class,
			eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument.EventDispatchNumberDisableAdapter.class,
			eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFeNumberDisable.class);
	
	private final String value;
	
	private final Class<? extends NFeTransmissionChannel> transmissionChannelClass;

	private final Class<? extends NFeDispatchAdapterVersion> nfeDispatchAdapterClass;

	private final Class<? extends ProcessedNFeVersion> processedNFeClass;
	
	private final Class<? extends ProcessedFiscalDocumentAdapterVersion> processedFiscalDocumentAdapterClass;
	
	private final Class<? extends ProcessedEventVersion> processedEventClass;
	
	private final Class<? extends EventDispatchCancelVersion> eventDispatchCancelClass;
	
	private final Class<? extends EventDispatchCCeVersion> eventDispatchCCeClass;
	
	private final Class<? extends EventDispatchNumberDisableVersion> eventDispatchNumberDisableClass;
	
	private final Class<? extends ProcessedNFeNumberDisableVersion> processedNumberDisableClass;
	
	

	public String getFormattedValue() {
		return this.value.replace('.', '_');
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
