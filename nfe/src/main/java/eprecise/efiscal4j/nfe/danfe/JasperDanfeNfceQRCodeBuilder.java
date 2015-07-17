
package eprecise.efiscal4j.nfe.danfe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;


public class JasperDanfeNfceQRCodeBuilder {

    private final ProcessedNFe nfe;

    public JasperDanfeNfceQRCodeBuilder(ProcessedNFe nfe) {
        this.nfe = nfe;
    }

    public String build() {

        final Params params = new Params(this.nfe);

        return "";
    }

    class Params {

        private final ProcessedNFe nfe;

        public Params(ProcessedNFe nfe) {
            this.nfe = nfe;
        }

        /*
         * chNFe
         */
        public String getAcessKey() {
            return this.nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo().getAcessKey();
        }

        /*
         * nVersao
         */
        public String getNFCeVersion() {
            return "100";
        }

        /*
         * tpAmb
         */
        public String getEnvironment() {
            return new Integer(this.nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo().getTransmissionEnvironment().getValue()).toString();
        }

        /*
         * cDest
         */
        public Optional<String> getReceiverCnp() {
            return Optional.ofNullable(this.nfe.getNfe().getNFeInfo().getReceiver()).map(r -> r.getDocuments().getCnpjCpf());
        }

        private String getToDateStr(String fromDate, String fromPattern, String toPattern) {
            try {
                return new SimpleDateFormat(toPattern).format(new SimpleDateFormat(fromPattern).parse(fromDate));
            } catch (final ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
