
package eprecise.efiscal4j.nfse.tc.curitiba;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.domain.adapters.CuritibaNFSeDomainAdapter;
import eprecise.efiscal4j.nfse.domain.comp.ProcessedNFSe;
import eprecise.efiscal4j.nfse.domain.comp.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.compNfse.CommonsGeneratorOrgan;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.rps.CuritibaServiceConstruction;
import eprecise.efiscal4j.nfse.tc.curitiba.intermediary.CuritibaServiceIntermediaryIdentifier;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.CuritibaNatureOperation;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.CuritibaSpecialTaxationRegime;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.services.CuritibaService;
import eprecise.efiscal4j.nfse.tc.curitiba.provider.CuritibaServiceProvider;
import eprecise.efiscal4j.nfse.tc.curitiba.taker.CuritibaServiceTaker;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeValue;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@XmlRootElement(name = "Nfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaNFSe extends ProcessedNFSe {

    private static final long serialVersionUID = 1L;
    
    public static final DateFormat EMISSION_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private final @XmlElement(name = "InfNfse") @NotNull CuritibaNFSeInfo info;
    
    @Override
    public String getNumber() {
        return Optional.ofNullable(info).map(i -> i.getNumber()).orElse(null);
    }

    @Override
    public String getVerificationCode() {
        return Optional.ofNullable(info).map(i -> i.getVerificationCode()).orElse(null);
    }

    @Override
    public Optional<String> getAccessKey() {
        return Optional.empty();
    }

    @Override
    public Date getEmissionDate() {
        return Optional.ofNullable(info).map(i -> i.getEmissionDate()).map(t -> {
            try {
                return CuritibaNFSeDomainAdapter.NFSE_DATETIME_FORMAT.parse(t);
            } catch (final ParseException e) {
                try {
                    return EMISSION_DATETIME_FORMAT.parse(t);
                } catch (ParseException e1) {
                    throw new RuntimeException(e1);
                }
            }
        }).orElse(null);
    }

    @Override
    public RpsIdentifier getRpsIdentifier() {
        return Optional.ofNullable(info).map(i -> i.getRpsIdentifier()).orElse(null);
    }

    @Override
    public CommonsGeneratorOrgan getGeneratorOrgan() {
        return Optional.ofNullable(info).map(i -> i.getGeneratorOrgan()).orElse(null);
    }

    @Override
    public String getProviderIm() {
        return Optional.ofNullable(info).map(i -> i.getServiceProvider().getIdentifier().getMunicipalRegistration()).orElse(null);
    }

    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @Getter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CuritibaNFSeInfo {

        private final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

        private final @NotNull @XmlElement(name = "CodigoVerificacao") @Size(min = 1, max = 9) String verificationCode;

        private final @NotNull @XmlElement(name = "DataEmissao") @NFSeDateTimeUTC String emissionDate;

        private final @XmlElement(name = "IdentificacaoRps") @NotNull CommonsRpsIdentifier rpsIdentifier;

        private final @NotNull @XmlElement(name = "DataEmissaoRps") @NFSeDate String rpsEmissionDate;

        private final @XmlElement(name = "NaturezaOperacao") @NotNull CuritibaNatureOperation natureOperation;

        private final @XmlElement(name = "RegimeEspecialTributacao") CuritibaSpecialTaxationRegime specialTaxationRegime;

        private final @XmlElement(name = "OptanteSimplesNacional") @NotNull CommonsNFSeBoolean simpleNational;

        private final @XmlElement(name = "IncentivadorCultural") @NotNull CommonsNFSeBoolean culturalPromoter;

        private final @NotNull @XmlElement(name = "Competencia") @NFSeDateTimeUTC String competence;

        private final @XmlElement(name = "NfseSubstituida") @NFSeNonNegativeInteger @Size(max = 15) String nfseSubstitutedNumber;

        private final @XmlElement(name = "OutrasInformacoes") @Size(min = 1, max = 255) String otherInformation;

        private final @NotNull @XmlElement(name = "Servico") CuritibaService service;

        private final @XmlElement(name = "ValorCredito") @NFSeValue String creditValue;

        private final @XmlElement(name = "PrestadorServico") @NotNull CuritibaServiceProvider serviceProvider;

        private final @XmlElement(name = "TomadorServico") @NotNull CuritibaServiceTaker serviceTaker;

        private final @XmlElement(name = "IntermediarioServico") CuritibaServiceIntermediaryIdentifier serviceIntermediaryIdentifier;

        private final @NotNull @XmlElement(name = "OrgaoGerador") CommonsGeneratorOrgan generatorOrgan;

        private final @XmlElement(name = "ConstrucaoCivil") CuritibaServiceConstruction construction;
        
    }

}
