
package eprecise.efiscal4j.nfse.tc.curitiba.lot.rps;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsServiceConstruction;
import eprecise.efiscal4j.nfse.tc.curitiba.intermediary.CuritibaServiceIntermediaryIdentifier;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.services.CuritibaService;
import eprecise.efiscal4j.nfse.tc.curitiba.provider.CuritibaServiceProviderIdentifier;
import eprecise.efiscal4j.nfse.tc.curitiba.taker.CuritibaServiceTaker;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlRootElement(name = "Rps")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaRps implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @NotNull @XmlElement(name = "InfRps") CuritibaRpsInfo info;

    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @XmlRootElement(name = "InfRps")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CuritibaRpsInfo {
        
        private @Getter final @XmlAttribute(name = "id") String id;
        
        private @Getter final @NotNull @XmlElement(name = "IdentificacaoRps") CommonsRpsIdentifier identifier;

        private @Getter final @NotNull @XmlElement(name = "DataEmissao") @NFSeDate String emissionDate;
        
        private @Getter final @NotNull @XmlElement(name = "NaturezaOperacao") CuritibaNatureOperation natureOperation;
        
        private @Getter final @XmlElement(name = "RegimeEspecialTributacao") CuritibaSpecialTaxationRegime specialTaxationRegime;
        
        private @Getter final @NotNull @XmlElement(name = "OptanteSimplesNacional") CommonsNFSeBoolean nationalSimple;
        
        private @Getter final @NotNull @XmlElement(name = "IncentivadorCultural") CommonsNFSeBoolean culturalPromoter;
        
        private @Getter final @NotNull @XmlElement(name = "Status") CommonsRpsStatus status;

        private @Getter final @XmlElement(name = "RpsSubstituido") CommonsRpsIdentifier substitutedIdentifier;

        private @Getter final @NotNull @XmlElement(name = "Servico") CuritibaService service;

        private @Getter final @NotNull @XmlElement(name = "Prestador") CuritibaServiceProviderIdentifier serviceProviderIdentifier;

        private @Getter final @XmlElement(name = "Tomador") CuritibaServiceTaker serviceTaker;

        private @Getter final @XmlElement(name = "Intermediario") CuritibaServiceIntermediaryIdentifier serviceIntermediary;

        private @Getter final @XmlElement(name = "ConstrucaoCivil") CommonsServiceConstruction construction;

    }
}
