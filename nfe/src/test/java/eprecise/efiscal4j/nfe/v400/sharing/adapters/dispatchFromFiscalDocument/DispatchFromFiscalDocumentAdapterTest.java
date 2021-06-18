
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.NFeTestParams;
import eprecise.efiscal4j.nfe.cnpAccessXml.CnpjAccessXml;
import eprecise.efiscal4j.nfe.cnpAccessXml.CpfAccessXml;
import eprecise.efiscal4j.nfe.domain.NFeDomainTest;
import eprecise.efiscal4j.nfe.emitter.CRT;
import eprecise.efiscal4j.nfe.emitter.Emitter;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddress;
import eprecise.efiscal4j.nfe.emitter.address.EmitterAddressCity;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterDocuments;
import eprecise.efiscal4j.nfe.emitter.documents.EmitterLegalEntityDocuments;
import eprecise.efiscal4j.nfe.v400.autXml.NFeAutXml;
import eprecise.efiscal4j.nfe.v400.autXml.NFeAutXmlCnpj;
import eprecise.efiscal4j.nfe.v400.autXml.NFeAutXmlCpf;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class DispatchFromFiscalDocumentAdapterTest {

    private final Certificate keyCertificate = new Certificate(() -> NFeTestParams.class.getResourceAsStream("/eprecise/efiscal4j/nfe/certificate/Teste.pfx"), "1234");

    private static final String BA_DEFAULT_CNPJ_AUT_XML = "13937073000156";

    private final CnpjAccessXml cnpjAccessXml = CnpjAccessXml.builder().cnpj("44522102000142").build();

    private final CpfAccessXml cpfAccessXml = CpfAccessXml.builder().cpf("20875820093").build();

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

    @Test
    public void NoCnpInformedOnBAState() throws Exception{
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().build();
        final List<NFeAutXml> nFeAutXmls = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAutXml();
        Assert.assertEquals( ((NFeAutXmlCnpj) nFeAutXmls.get(0)).getCnpj(), BA_DEFAULT_CNPJ_AUT_XML);
    }

    @Test
    public void CnpjInformedOnBAState() throws Exception {
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().cnpAccessXmls(Arrays.asList(this.cnpjAccessXml)).build();
        final List<NFeAutXml> nFeAutXmls = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAutXml();
        Assert.assertEquals( ((NFeAutXmlCnpj) nFeAutXmls.get(0)).getCnpj(), cnpjAccessXml.getCnp());
    }

    @Test
    public void CpfInformedOnBAState() throws Exception {
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().cnpAccessXmls(Arrays.asList(this.cpfAccessXml)).build();
        final List<NFeAutXml> nFeAutXmls = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAutXml();
        Assert.assertEquals( ((NFeAutXmlCpf) nFeAutXmls.get(0)).getCpf(), cpfAccessXml.getCnp());
    }

    @Test
    public void TwoCnpInformedOnBAState() throws Exception {
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().cnpAccessXmls(Arrays.asList(this.cnpjAccessXml, this.cpfAccessXml)).build();
        final List<NFeAutXml> nFeAutXmls = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAutXml();
        nFeAutXmls.forEach(nFeAutXml -> {
            if(nFeAutXml instanceof NFeAutXmlCnpj){
                Assert.assertEquals(((NFeAutXmlCnpj) nFeAutXml).getCnpj(), cnpjAccessXml.getCnp());
            } else {
                Assert.assertEquals(((NFeAutXmlCpf) nFeAutXml).getCpf(), cpfAccessXml.getCnp());
            }
        } );
    }

    @Test
    public void NoCnpInformedOnPRState() throws  Exception{
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().emitter(buildEmitterPR()).build();
        final List<NFeAutXml> nFeAutXmls = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAutXml();
        Assert.assertNull(nFeAutXmls);
    }

    @Test
    public void CnpjInformedOnPRState() throws  Exception{
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().emitter(buildEmitterPR()).cnpAccessXmls(Arrays.asList(this.cnpjAccessXml)).build();
        final List<NFeAutXml> nFeAutXmls = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAutXml();
        Assert.assertEquals( ((NFeAutXmlCnpj) nFeAutXmls.get(0)).getCnpj(), cnpjAccessXml.getCnp());
    }

    @Test
    public void CpfInformedOnPRState() throws  Exception{
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().emitter(buildEmitterPR()).cnpAccessXmls(Arrays.asList(this.cpfAccessXml)).build();
        final List<NFeAutXml> nFeAutXmls = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAutXml();
        Assert.assertEquals( ((NFeAutXmlCpf) nFeAutXmls.get(0)).getCpf(), cpfAccessXml.getCnp());
        Assert.assertEquals( ((NFeAutXmlCpf) nFeAutXmls.get(0)).getCnp(), cpfAccessXml.getCnp());
    }

    @Test
    public void TwoCnpInformedOnPRState() throws Exception {
        final NFe nfe = new NFeDomainTest().getDefaultNFeBuilder().emitter(buildEmitterPR()).cnpAccessXmls(Arrays.asList(this.cnpjAccessXml, this.cpfAccessXml)).build();
        final List<NFeAutXml> nFeAutXmls = new DispatchFromFiscalDocumentAdapter(nfe, this.keyCertificate).buildNFeDispatch().getnFes().stream().findFirst().get().getNFeInfo().getAutXml();
        nFeAutXmls.forEach(nFeAutXml -> {
            if(nFeAutXml instanceof NFeAutXmlCnpj){
                Assert.assertEquals(((NFeAutXmlCnpj) nFeAutXml).getCnp(), cnpjAccessXml.getCnp());
            } else {
                Assert.assertEquals(((NFeAutXmlCpf) nFeAutXml).getCpf(), cpfAccessXml.getCnp());
            }
        } );
    }

    private Emitter buildEmitterPR() {
        return Emitter.builder().documents(this.buildEmitterDocumentsPR()).crt(CRT.SIMPLE_NATIONAL).address(this.buildEmitterAddressPR()).build();
    }

    private EmitterAddress buildEmitterAddressPR() {
        // @formatter:off
        return EmitterAddress.builder()
                .cep("81850040")
                .street("Jeronimo Thadeo")
                .district("Alto Boqueirao")
                .city(EmitterAddressCity.builder().ibgeCode("2927408").description("Curitiba").uf(UF.PR).build()).number("100").build();
        // @formatter:on
    }

    private EmitterDocuments buildEmitterDocumentsPR() {
        // @formatter:off
        return EmitterLegalEntityDocuments.builder()
                .name("Nome emitente teste")
                .ie(NFeTestParams.getEmitterIe().orElse("000000"))
                .cnpj(NFeTestParams.getEmitterCnpj().orElse("14241297000191"))
                .fancyName("Nome emitente teste")
                .build();
        // @formatter:on
    }
}