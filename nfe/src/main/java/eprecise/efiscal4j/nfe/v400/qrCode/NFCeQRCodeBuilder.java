
package eprecise.efiscal4j.nfe.v400.qrCode;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import eprecise.efiscal4j.nfe.v400.NFe;
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
        .append(this.getEnvironment())
        .append("|")
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
