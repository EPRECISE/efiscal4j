
package eprecise.efiscal4j.nfe.v400.qrCode;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

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
        .append(this.getAcessKey())
        .append("|")
        .append(this.getNFCeVersion())
        .append("|")
        .append(this.getEnvironment());

        if(this.nfe.getNFeInfo().getnFeIdentification().getnFeTransmissionMethod().equals(NFeTransmissionMethod.CONTINGENCIA_OFF_LINE_NFCE)) {
            params.append("|")
            .append(this.getEmissionDay())
            .append("|")
            .append(this.getNFeTotalValue())
            .append("|")
            .append(this.getDigestValue());
        }

        params.append("|")
        .append(this.getCIdToken());

        final String qrCodeSha1 = Hashing.sha1().hashString(new StringBuilder(params.toString()).append(this.getCsc()).toString(), Charsets.UTF_8).toString();

        return url.append("?p=")
                .append(params.toString())
                .append("|")
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
        return "2";
    }

    /*
     * tpAmb
     */
    private String getEnvironment() {
        return new Integer(this.nfe.getNFeInfo().getnFeIdentification().getTransmissionEnvironment().getValue()).toString();
    }

    private String getEmissionDay() {
        return this.nfe.getNFeInfo().getnFeIdentification().getEmissionDateTime().substring(8, 10);
    }

    private String getNFeTotalValue() {
        return this.nfe.getNFeInfo().getnFeTotal().getIcmsTotal().getNfeTotalValue();
    }

    private String getDigestValue() {
        return this.getHex(Base64.getEncoder().encodeToString(this.nfe.getSignature().getSignedInfo().getReference().getDigestValue()).getBytes());
    }

    private String getHex(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (byte aByte : bytes) {
            int up = ((aByte >> 4) & 0xf) << 4;
            int down = aByte & 0xf;
            if (up == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(up | down));
        }
        return s.toString();
    }

    /*
     * cIdToken
     */
    private String getCIdToken() {
        return StringUtils.stripStart(this.csc.getCldToken(), "0");
    }

    /*
     * CSC
     */
    private String getCsc() {
        return this.csc.getCscValue();
    }

}
