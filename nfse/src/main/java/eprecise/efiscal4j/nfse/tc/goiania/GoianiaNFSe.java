
package eprecise.efiscal4j.nfse.tc.goiania;

import eprecise.efiscal4j.nfse.domain.comp.ProcessedNFSe;
import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.compNfse.CommonsGeneratorOrgan;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaIdentifier;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaNFSeStatementProvisionService;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaProvider;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeValue;
import eprecise.efiscal4j.signer.domain.SignatureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlRootElement(name = "tcNfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaNFSe extends ProcessedNFSe {

    private static final long serialVersionUID = 1L;

    public static final DateFormat EMISSION_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private static final String GOIANIA_IBGE_CODE = "5208707";

    private @Getter final @XmlElement(name = "InfNfse") @NotNull GoianiaNFSeInfo info;

    private @Getter final @XmlElement(name = "versao") String version;

    @Builder
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GoianiaNFSeInfo {

        private @Getter final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

        private @Getter final @NotNull @XmlElement(name = "CodigoVerificacao") @Size(min = 1, max = 9) String verificationCode;

        private @Getter final @NotNull @XmlElement(name = "DataEmissao") @NFSeDateTimeUTC String emissionDate;

        private @Getter final @XmlElement(name = "NfseSubstituida") @NFSeNonNegativeInteger @Size(max = 15) String nfseSubstitutedNumber;

        private @Getter final @XmlElement(name = "OutrasInformacoes") @Size(min = 1, max = 255) String otherInformation;

        private @Getter final @NotNull @XmlElement(name = "ValoresNfse") GoianiaNFSeValues nfseValues;

        private @Getter final @XmlElement(name = "ValorCredito") @NFSeValue String creditValue;

        private @Getter final @XmlElement(name = "OrgaoGerador") CommonsGeneratorOrgan generatorOrgan;

        private @Getter final @XmlElement(name = "DeclaracaoPrestacaoServico") GoianiaNFSeStatementProvisionService statementProvisionService;
        
        private @Getter @XmlElement(name = "Signature") SignatureType signature;
    }
    
    @Override
    public String getProviderCnp() {
        return Optional.ofNullable(info).map(GoianiaNFSeInfo::getStatementProvisionService)
                .map(GoianiaNFSeStatementProvisionService::getServiceProviderIdentifier)
                .map(GoianiaProvider::getIdentifier)
                .map(GoianiaIdentifier::getCnp).map(CommonsNFSeCnp::getCnp).orElse(null);
    }

    @Override
    public String getProviderIm() {
        return Optional.ofNullable(info).map(GoianiaNFSeInfo::getStatementProvisionService)
                .map(GoianiaNFSeStatementProvisionService::getServiceProviderIdentifier)
                .map(GoianiaProvider::getIdentifier)
                .map(GoianiaIdentifier::getMunicipalRegistration).orElse(null);
    }

    @Override
    public String getNumber() {
        return Optional.ofNullable(info).map(GoianiaNFSeInfo::getNumber).orElse(null);
    }

    @Override
    public String getVerificationCode() {
        return Optional.ofNullable(info).map(GoianiaNFSeInfo::getVerificationCode).orElse(null);
    }

    @Override
    public Optional<String> getAccessKey() {
        return Optional.empty();
    }

    @Override
    public Date getEmissionDate() {
        return Optional.ofNullable(info).map(GoianiaNFSeInfo::getEmissionDate).map(d -> {
            try {
                return GoianiaNFSe.EMISSION_DATETIME_FORMAT.parse(d);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).orElse(null);
    }

    @Override
    public RpsIdentifier getRpsIdentifier() {
        return Optional.ofNullable(info).map(GoianiaNFSeInfo::getStatementProvisionService)
                .map(GoianiaNFSeStatementProvisionService::getIdentifier).orElse(null);
    }

    @Override
    public CommonsGeneratorOrgan getGeneratorOrgan() {
        try {
            return new CommonsGeneratorOrgan.Builder().withCityCode(GOIANIA_IBGE_CODE).withUf(CommonsNFSeUF.GO).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
