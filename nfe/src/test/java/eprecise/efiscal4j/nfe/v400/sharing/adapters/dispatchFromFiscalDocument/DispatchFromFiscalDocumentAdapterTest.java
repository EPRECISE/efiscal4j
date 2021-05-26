
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.NFeTestParams;
import eprecise.efiscal4j.nfe.domain.NFeDomainTest;


public class DispatchFromFiscalDocumentAdapterTest {

    private final Certificate keyCertificate = new Certificate(() -> NFeTestParams.class.getResourceAsStream("/eprecise/efiscal4j/nfe/certificate/Teste.pfx"), "1234");

    @Test
    public void formatNFeStringTest1() throws Exception {
        final String input = "Teste informação adicional com tres espaços agora   pronto.. Agora outra com quatro espaços, la vai    feito.\n"
                + "Agora      em  outra   linha       com varios  espaços   diferentes  e    alguns caracteres agora Ÿ ƒ   • pronto.\n" + "E outra linha";
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().details(input).build();
        final String details = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAdditionalInfo().getComplementaryInfo();
        Assert.assertEquals(details,
                "TESTE INFORMACAO ADICIONAL COM TRES ESPACOS AGORA PRONTO.. AGORA OUTRA COM QUATRO ESPACOS, LA VAI FEITO.  AGORA EM OUTRA LINHA COM VARIOS ESPACOS DIFERENTES E ALGUNS CARACTERES AGORA Y PRONTO.  E OUTRA LINHA");
    }

    @Test
    public void formatNFeStringTest2() throws Exception {
        final String input = "Esse é outro teste    com  vários espaços e  agora\n"
                + "em outra linha caracteres especiais.. ♦🍬  Įˢ𝐬Ｏ é υ𝕞 𝐭𝓔𝕤𝔱ｅ ⓒⓄм ᑕᗩ𝓡𝒶Ⓒ𝓉ⒺŘⒺ𝐬 ⓔş𝐏€ⓒⓘᗩ𝒾ˢ  😲🐤";
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().details(input).build();
        final String details = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAdditionalInfo().getComplementaryInfo();
        Assert.assertEquals(details, "ESSE E OUTRO TESTE COM VARIOS ESPACOS E AGORA  EM OUTRA LINHA CARACTERES ESPECIAIS.. I E R S");
    }

}
