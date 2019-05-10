
package eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument;

import java.text.ParseException;
import java.util.Optional;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.FiscalDocumentNumberDisable;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.serie.FiscalDocumentSerie;
import eprecise.efiscal4j.nfe.serie.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.v400.sharing.NFeNumberDisableInfo;
import eprecise.efiscal4j.nfe.v400.sharing.NFeNumberDisableResponseInfo;
import eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFeNumberDisable;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;


public class ProcessedFiscalDocumentNumberDisableAdapter {

    private final ProcessedNFeNumberDisable processedNFeNumberDisable;

    public ProcessedFiscalDocumentNumberDisableAdapter(final ProcessedNFeNumberDisable processedNFeNumberDisable) {
        this.processedNFeNumberDisable = processedNFeNumberDisable;
    }

    public FiscalDocumentNumberDisable.Processed buildProcessedFiscalDocumentNumberDisable() {
        try {
            return FiscalDocumentNumberDisable.Processed.builder().id(this.getProcessedNFeNumberDisableResponseInfo().getId())
                    .date(NFeDateTimeUTC.dateFormat.parse(this.getProcessedNFeNumberDisableResponseInfo().getReceptionDateTime()))
                    .protocolNumber(this.getProcessedNFeNumberDisableResponseInfo().getProtocolNumber()).status(EventStatus.builder()
                            .statusCode(this.getProcessedNFeNumberDisableResponseInfo().getStatusCode()).statusDescription(this.getProcessedNFeNumberDisableResponseInfo().getReason()).build())
                    .document(this.buildDocument()).build();
        } catch (final ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private FiscalDocumentNumberDisable buildDocument() {
       //@formatter:off
        return FiscalDocumentNumberDisable.builder()
                .version(FiscalDocumentSupportedVersion.VERSION_4_00)
                .serie(this.buildSerie())
                .emitter(this.buildEmitter())
                .model(Optional.ofNullable(this.getProcessedNFeNumberDisableRquestInfo()).map(i->i.getFiscalDocumentModel()).map(m -> FiscalDocumentModel.findByCode(m.getValue())).orElse(null))
                .year(Integer.valueOf(this.getProcessedNFeNumberDisableRquestInfo().getYear()))
                .beginNumber(Integer.valueOf(this.getProcessedNFeNumberDisableRquestInfo().getBeginNumber()))
                .endNumber(Integer.valueOf(this.getProcessedNFeNumberDisableRquestInfo().getEndNumber()))
                .justification(this.getProcessedNFeNumberDisableRquestInfo().getJustification())
                .build();
      //@formatter:on
    }

    private FiscalDocumentSerie buildSerie() {
        // @formatter:off
           return FiscalDocumentSerie.builder()
                   .number(Optional.ofNullable(this.getProcessedNFeNumberDisableRquestInfo().getFiscalDocumentSeries()).map(Integer::parseInt).orElse(null))
                   .environment(Optional.ofNullable(this.getProcessedNFeNumberDisableRquestInfo().getTransmissionEnvironment()).map(t -> TransmissionEnvironment.findBy(t.getValue()).orElse(null)).orElse(null))
                   .build();
        // @formatter:on
    }

    private NFeNumberDisableInfo getProcessedNFeNumberDisableRquestInfo() {
        return this.processedNFeNumberDisable.getNfeNumberDisable().getInfo();
    }

    private NFeNumberDisableResponseInfo getProcessedNFeNumberDisableResponseInfo() {
        return this.processedNFeNumberDisable.getNfeNumberDisableResponse().getInfo();
    }

    private FiscalDocumentNumberDisable.Emitter buildEmitter() {
        // @formatter:off
            return FiscalDocumentNumberDisable.Emitter.builder()
                    .cnpj(this.processedNFeNumberDisable.getNfeNumberDisable().getInfo().getCnpj())
                    .uf(UF.findByAcronym(this.processedNFeNumberDisable.getNfeNumberDisable().getInfo().getUfIbgeCode().getAcronym()))
                    .build();
        // @formatter:on
    }

}
