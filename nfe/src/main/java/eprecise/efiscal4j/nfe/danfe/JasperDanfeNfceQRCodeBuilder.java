
package eprecise.efiscal4j.nfe.danfe;

import java.math.BigInteger;
import java.util.Optional;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.NFeTransmissionMethod;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;


public class JasperDanfeNfceQRCodeBuilder {

    private final ProcessedNFe nfe;

    public JasperDanfeNfceQRCodeBuilder(ProcessedNFe nfe) {
        this.nfe = nfe;
    }

    public String build() {

        final StringBuilder url = new StringBuilder(JasperDanfeNfceUrlPath.SERVICE.getUrl(this.nfe.getNfe().getNFeInfo().getEmitter().getAdress().getCity().getUf(), this.nfe
                .getProcessingStatusProtocol().getProcessingStatusProtocolInfo().getTransmissionEnvironment()));

        //@formatter:off
        final StringBuilder params = new StringBuilder()
        .append("chNFe=")
        .append(getAcessKey()).append("&nVersao=")
        .append(getNFCeVersion())
        .append("&tpAmb=")
        .append(getEnvironment());
        getReceiverCnp().ifPresent(r->{
            params.append("&cDest=")
            .append(r);
        });
        params.append("&dhEmi=")
        .append(getEmissionDateTimeHex())
        .append("&vNF=")
        .append(getNFValue())
        .append("&vICMS=")
        .append(getICMSValue())
        .append("&digVal=")
        .append(getDigestValueHexOrSha1())
        .append("&cIdToken=")
        .append(getCIdToken());

        final String qrCodeSha1 = Hashing.sha1().hashString(new StringBuilder(params.toString()).append(getCsc()).toString(), Charsets.UTF_8).toString();
        final String qrCodeHex = String.format("%040x", new BigInteger(1, qrCodeSha1.getBytes()));

        return url.append("?")
                .append(params.toString())
                .append("&cHashQRCode=")
                .append(qrCodeHex).toString();


        //@formatter:on
    }

    /*
     * chNFe
     */
    private String getAcessKey() {
        return this.nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo().getAcessKey();
    }

    /*
     * nVersao
     */
    private String getNFCeVersion() {
        return "100";
    }

    /*
     * tpAmb
     */
    private String getEnvironment() {
        return new Integer(this.nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo().getTransmissionEnvironment().getValue()).toString();
    }

    /*
     * cDest
     */
    private Optional<String> getReceiverCnp() {
        return Optional.ofNullable(this.nfe.getNfe().getNFeInfo().getReceiver()).map(r -> r.getDocuments().getCnpjCpf());
    }

    /*
     * dhEmi
     */
    private String getEmissionDateTimeHex() {
        return String.format("%040x", new BigInteger(1, this.nfe.getNfe().getNFeInfo().getnFeIdentification().getEmissionDateTime().getBytes()));
    }

    /*
     * vNF
     */
    private String getNFValue() {
        return this.nfe.getNfe().getNFeInfo().getnFeTotal().getIcmsTotal().getNfeTotalValue();
    }

    /*
     * vICMS
     */
    private String getICMSValue() {
        return this.nfe.getNfe().getNFeInfo().getnFeTotal().getIcmsTotal().getIcmsTotalValue();
    }

    /*
     * digVal
     */
    private String getDigestValueHexOrSha1() {
        if (this.nfe.getNfe().getNFeInfo().getnFeIdentification().getnFeTransmissionMethod().equals(NFeTransmissionMethod.NORMAL)) {
            return String.format("%040x", new BigInteger(1, this.nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo().getDigestValue().getBytes()));
        }
        if (this.nfe.getNfe().getNFeInfo().getnFeIdentification().getnFeTransmissionMethod().equals(NFeTransmissionMethod.CONTINGENCIA_OFF_LINE_NFCE)) {
            return Hashing.sha1().hashString(new FiscalDocumentSerializer<>(this.nfe).serialize(), Charsets.UTF_8).toString();
        } else {
            throw new UnsupportedOperationException(this.nfe.getNfe().getNFeInfo().getnFeIdentification().getnFeTransmissionMethod().toString());
        }
    }

    /*
     * cIdToken
     */
    private String getCIdToken() {
        return "123456";
    }

    /*
     * CSC
     */
    private String getCsc() {
        return "7439434e7a67536f48596430440654056406";
    }

}
