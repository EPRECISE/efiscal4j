
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocumentNumberDisable;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.sharing.NFeNumberDisableDispatch;
import eprecise.efiscal4j.nfe.v400.sharing.NFeNumberDisableInfo;
import eprecise.efiscal4j.nfe.version.EventDispatchNumberDisableVersion;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class EventDispatchNumberDisableAdapter implements EventDispatchNumberDisableVersion {

    private final FiscalDocumentNumberDisable fiscalDocumentNumberDisable;

    private final Certificate certificate;

    public EventDispatchNumberDisableAdapter(final FiscalDocumentNumberDisable fiscalDocumentNumberDisable, final Certificate certificate) {
        this.fiscalDocumentNumberDisable = fiscalDocumentNumberDisable;
        this.certificate = certificate;
    }

    @Override
    public NFeNumberDisableDispatch buildEventDispatchNumberDisable() {
        try {
            return new NFeNumberDisableDispatch.Builder().withInfo(this.buildNFeInfo()).build(new DefaultSigner(this.certificate));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private NFeNumberDisableInfo buildNFeInfo() throws Exception {
      //@formatter:off
        return new NFeNumberDisableInfo.Builder()
                .withCnpj(Optional.ofNullable(this.fiscalDocumentNumberDisable.getEmitter()).map(e->e.getCnpj()).orElse(null))
                .withFiscalDocumentModel(this.fiscalDocumentNumberDisable.getModel())
                .withFiscalDocumentSeries(Optional.ofNullable(this.fiscalDocumentNumberDisable.getSerie()).map(s->s.getNumber()).map(String::valueOf).orElse(null))
                .withTransmissionEnvironment(Optional.ofNullable(this.fiscalDocumentNumberDisable.getSerie()).map(s -> s.getEnvironment()).map(e->TransmissionEnvironment.findBy(e.getValue()).orElse(null)).orElse(null))
                .withUfIbgeCode(Optional.ofNullable(this.fiscalDocumentNumberDisable.getEmitter()).map(e->e.getUf()).orElse(null))
                .withYear(Optional.ofNullable(this.fiscalDocumentNumberDisable.getYear()).map(y-> y % 100).map(String::valueOf).orElse(null))
                .withBeginNumber(Optional.ofNullable(this.fiscalDocumentNumberDisable.getBeginNumber()).map(String::valueOf).orElse(null))
                .withEndNumber(Optional.ofNullable(this.fiscalDocumentNumberDisable.getEndNumber()).map(String::valueOf).orElse(null))
                .withJustification(this.formatNFeString(this.fiscalDocumentNumberDisable.getJustification(), 255))
                .build();
      //@formatter:on
    }

    private String formatNFeString(final String input, final int size) {
        return Optional.ofNullable(this.nullIfEmpty(input)).filter(Objects::nonNull).map(string -> {
            return StringUtils.upperCase(StringUtils.stripAccents(EventDispatchNumberDisableAdapter.abbreviate(string.replaceAll("\n", "  ").replaceAll("\r", "  ").replace("\t", "  "), size)));
        }).orElse(null);
    }

    private static String abbreviate(final String input, final int size) {
        if ((input != null) && !input.isEmpty()) {
            if (size >= 4) {
                return StringUtils.abbreviate(input, size);
            } else if (input.length() > size) {
                return input.substring(0, size);
            }
        }
        return input;

    }

    private String nullIfEmpty(final String v) {
        return StringUtils.isEmpty(v) ? null : v;
    }

}
