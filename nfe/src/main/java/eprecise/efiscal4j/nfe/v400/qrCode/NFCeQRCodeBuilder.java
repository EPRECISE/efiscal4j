
package eprecise.efiscal4j.nfe.v400.qrCode;

import java.math.BigInteger;
import java.util.Base64;
import java.util.Optional;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.v400.NFe;
import eprecise.efiscal4j.nfe.v400.NFeTransmissionMethod;
import eprecise.efiscal4j.nfe.v400.danfe.JasperDanfeNfceUrlPath;
import eprecise.efiscal4j.nfe.v400.nfce.CSC;


public class NFCeQRCodeBuilder {

    private final NFe nfe;

    private final CSC csc;

    public NFCeQRCodeBuilder(final NFe nfe, final CSC csc) {
        this.nfe = nfe;
        this.csc = csc;
    }

    public String build() {

        final StringBuilder url = new StringBuilder(
                JasperDanfeNfceUrlPath.SERVICE.getUrl(this.nfe.getNFeInfo().getEmitter().getAdress().getCity().getUf(), this.nfe.getNFeInfo().getnFeIdentification().getTransmissionEnvironment()));

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

        return url.append("?")
                .append(params.toString())
                .append("&cHashQRCode=")
                .append(qrCodeSha1).toString();


        //@formatter:on
    }

    /*
     * chNFe
     */
    private String getAcessKey() {
        return this.nfe.getNFeInfo().getId().replaceAll("NFe", "");
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
        return new Integer(this.nfe.getNFeInfo().getnFeIdentification().getTransmissionEnvironment().getValue()).toString();
    }

    /*
     * cDest
     */
    private Optional<String> getReceiverCnp() {
        return Optional.ofNullable(this.nfe.getNFeInfo().getReceiver()).map(r -> r.getDocuments().getCnpjCpf());
    }

    /*
     * dhEmi
     */
    private String getEmissionDateTimeHex() {
        return String.format("%040x", new BigInteger(1, this.nfe.getNFeInfo().getnFeIdentification().getEmissionDateTime().getBytes()));
    }

    /*
     * vNF
     */
    private String getNFValue() {
        return this.nfe.getNFeInfo().getnFeTotal().getIcmsTotal().getNfeTotalValue();
    }

    /*
     * vICMS
     */
    private String getICMSValue() {
        return this.nfe.getNFeInfo().getnFeTotal().getIcmsTotal().getIcmsTotalValue();
    }

    /*
     * digVal
     */
    private String getDigestValueHexOrSha1() {

        final String digestValueTOBase64 = Base64.getEncoder().encodeToString(this.nfe.getSignature().getSignedInfo().getReference().getDigestValue());
        if (this.nfe.getNFeInfo().getnFeIdentification().getnFeTransmissionMethod().equals(NFeTransmissionMethod.NORMAL)) {
            return String.format("%x", new BigInteger(1, digestValueTOBase64.getBytes()));
        }
        if (this.nfe.getNFeInfo().getnFeIdentification().getnFeTransmissionMethod().equals(NFeTransmissionMethod.CONTINGENCIA_OFF_LINE_NFCE)) {
            return Hashing.sha1().hashString(new FiscalDocumentSerializer<>(this.nfe).serialize(), Charsets.UTF_8).toString();
        } else {
            throw new UnsupportedOperationException(this.nfe.getNFeInfo().getnFeIdentification().getnFeTransmissionMethod().toString());
        }
    }

    /*
     * cIdToken
     */
    private String getCIdToken() {
        return this.csc.getCldToken();
    }

    /*
     * CSC
     */
    private String getCsc() {
        return this.csc.getCscValue();
    }

}
