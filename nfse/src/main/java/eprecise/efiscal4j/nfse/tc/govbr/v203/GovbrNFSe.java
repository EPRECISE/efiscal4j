
package eprecise.efiscal4j.nfse.tc.govbr.v203;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.domain.comp.ProcessedNFSe;
import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.compNfse.CommonsGeneratorOrgan;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrIdentifier;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrServiceProvider;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.GovbrStatementProvisionService;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.statements.rps.GovbrRps;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.govbr.types.GovbrVersion;
import eprecise.efiscal4j.nfse.ts.govbr.types.NFSeValue;
import eprecise.efiscal4j.signer.domain.SignatureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlRootElement(name = "tcNfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSe extends ProcessedNFSe {

    private static final long serialVersionUID = 1L;

    public static final DateFormat EMISSION_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private @Getter @Builder.Default final @XmlAttribute(name = "versao") GovbrVersion version = GovbrVersion.VERSION_2_03;

    private @Getter final @XmlElement(name = "InfNfse") @NotNull GovbrNFSeInfo info;

    @Builder
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GovbrNFSeInfo {

        private @Getter final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

        private @Getter final @NotNull @XmlElement(name = "CodigoVerificacao") @Size(min = 1, max = 9) String verificationCode;

        private @Getter final @NotNull @XmlElement(name = "DataEmissao") @NFSeDateTimeUTC String emissionDate;

        private @Getter final @XmlElement(name = "NfseSubstituida") @NFSeNonNegativeInteger @Size(max = 15) String nfseSubstitutedNumber;

        private @Getter final @XmlElement(name = "OutrasInformacoes") @Size(min = 1, max = 255) String otherInformation;

        private @Getter final @NotNull @XmlElement(name = "ValoresNfse") GovbrNFSeValues nfseValues;

        private @Getter final @XmlElement(name = "ValorCredito") @NFSeValue String creditValue;

        private @Getter final @NotNull @XmlElement(name = "PrestadorServico") GovbrServiceProvider serviceProvider;

        private @Getter final @NotNull @XmlElement(name = "OrgaoGerador") CommonsGeneratorOrgan generatorOrgan;

        private @Getter final @NotNull @XmlElement(
                name = "DeclaracaoPrestacaoServico") GovbrStatementProvisionService statementProvisionService;
        
        private @Getter @XmlElement(name = "Signature") SignatureType signature;
    }

    @Override
    public String getProviderIm() {
        return Optional.ofNullable(info).map(GovbrNFSeInfo::getStatementProvisionService).map(GovbrStatementProvisionService::getInfo)
                .map(GovbrStatementProvisionService.GovbrStatementProvisionServiceInfo::getServiceProviderIdentifier)
                .map(GovbrIdentifier::getMunicipalRegistration).orElse(null);
    }

    @Override
    public String getNumber() {
        return Optional.ofNullable(info).map(GovbrNFSeInfo::getNumber).orElse(null);
    }

    @Override
    public String getVerificationCode() {
        return Optional.ofNullable(info).map(GovbrNFSeInfo::getVerificationCode).orElse(null);
    }

    @Override
    public Optional<String> getAccessKey() {
        return Optional.empty();
    }

    @Override
    public Date getEmissionDate() {
        return Optional.ofNullable(info).map(GovbrNFSeInfo::getEmissionDate).map(d -> {
            try {
                return GovbrNFSe.EMISSION_DATETIME_FORMAT.parse(d);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).orElse(null);
    }

    @Override
    public RpsIdentifier getRpsIdentifier() {
        return Optional.ofNullable(info).map(GovbrNFSeInfo::getStatementProvisionService).map(GovbrStatementProvisionService::getInfo)
                .map(GovbrStatementProvisionService.GovbrStatementProvisionServiceInfo::getRps).map(GovbrRps::getIdentifier).orElse(null);
    }

    @Override
    public CommonsGeneratorOrgan getGeneratorOrgan() {
        return Optional.ofNullable(info).map(GovbrNFSeInfo::getGeneratorOrgan).orElse(null);
    }

}
