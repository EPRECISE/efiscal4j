
package eprecise.efiscal4j.nfse.tc.govbr.v203.person.address;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeUF;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Getter final @XmlElement(name = "Endereco") @Size(
            min = 1, max = 125, message = "Endereço - rua: tamanho informado deve estar entre 1 e 125 caracteres.") String address;

    private @Getter final @XmlElement(name = "Numero") @Size(
            min = 1, max = 10, message = "Endereço - número: tamanho informado deve estar entre 1 e 10 caracteres.") String number;

    private @Getter final @XmlElement(name = "Complemento") @Size(
            min = 1, max = 60, message = "Endereço - complemento: tamanho informado deve estar entre 1 e 60 caracteres.") String complement;

    private @Getter final @XmlElement(name = "Bairro") @Size(
            min = 1, max = 60, message = "Endereço - bairro: tamanho informado deve estar entre 1 e 60 caracteres.") String district;

    private @Getter final @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(
            min = 1, max = 7,
            message = "Endereço - código do município: tamanho informado deve estar entre 1 e 7 caracteres.") String cityCode;

    private @Getter final @XmlElement(name = "Uf") CommonsNFSeUF uf;

    private @Getter final @XmlElement(name = "CodigoPais") @NFSeNonNegativeInteger @Size(
            min = 4, max = 4,
            message = "Endereço - código do país: tamanho informado deve possuir exatamente 4 caracteres.") String countryCode;

    private @Getter final @XmlElement(name = "Cep") @Size(
            min = 8, max = 8, message = "Endereço - cep: tamanho informado deve possuir exatamente 8 caracteres.") String cep;

}
