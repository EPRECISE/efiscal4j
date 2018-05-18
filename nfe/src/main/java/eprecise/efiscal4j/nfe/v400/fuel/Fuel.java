
package eprecise.efiscal4j.nfe.v400.fuel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Max100;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1204Temperature;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Grupo do detalhamento de operações com combustíveis líquidos
 *
 * @author Fernando C Glizt
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Fuel implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "cProdANP") @NotNull @Pattern(regexp = "[0-9]{9}") @NFeString final String anpProductCode;

    private @XmlElement(name = "descANP") @NotNull @Size(min = 2, max = 95) @NFeString final String anpProductDescription;

    private @XmlElement(name = "pGLP") @NFeDecimal0302a04Max100 final String glpPercent;

    private @XmlElement(name = "pGNn") @NFeDecimal0302a04Max100 final String gnnPercent;

    private @XmlElement(name = "pGNi") @NFeDecimal0302a04Max100 final String gniPercent;

    private @XmlElement(name = "vPart") @NFeDecimal1302 final String startingValue;

    private @XmlElement(name = "CODIF") @Pattern(regexp = "[0-9]{9}") @NFeString final String codifCode;

    private @XmlElement(name = "qTemp") @NFeDecimal1204Temperature final String temperature;

    private @XmlElement(name = "UFCons") final UF consumerUF;

    private @XmlElement(name = "CIDE") @NotNull final FuelCide cide;

    private @XmlElement(name = "encerrante") final FuelClosing closing;

    public static class Builder {

        private String anpProductCode;

        private String anpProductDescription;

        private String glpPercent;

        private String gnnPercent;

        private String gniPercent;

        private String startingValue;

        private String codifCode;

        private String temperature;

        private UF consumerUF;

        private FuelCide cide;

        private FuelClosing closing;

        /**
         * Código de produto da ANP. codificação de produtos do SIMP (http://www.anp.gov.br)
         *
         * @param anpProductCode
         * @return
         */
        public Builder withAnpProductCode(final String anpProductCode) {
            this.anpProductCode = anpProductCode;
            return this;
        }

        /**
         * Descrição do Produto conforme ANP. Utilizar a descrição de produtos do Sistema de Informações de Movimentação de Produtos - SIMP (http://www.anp.gov.br/simp/)
         *
         * @param anpProductDescription
         * @return
         */
        public Builder withAnpProductDescription(final String anpProductDescription) {
            this.anpProductDescription = anpProductDescription;
            return this;
        }

        /**
         * Percentual do GLP derivado do petróleo no produto GLP (cProdANP=210203001). Informar em número decimal o percentual do GLP derivado de petróleo no produto GLP. Valores 0 a 100
         *
         * @param glpPercent
         * @return
         */
        public Builder withGlpPercent(final String glpPercent) {
            this.glpPercent = glpPercent;
            return this;
        }

        /**
         * Percentual de gás natural nacional - GLGNn para o produto GLP (cProdANP=210203001). Informar em número decimal o percentual do Gás Natural Nacional - GLGNn para o produto GLP. Valores de 0
         * a 100
         *
         * @param gnnPercent
         * @return
         */
        public Builder withGnnPercent(final String gnnPercent) {
            this.gnnPercent = gnnPercent;
            return this;
        }

        /**
         * Percentual de gás natural importado GLGNi para o produto GLP (cProdANP=210203001). Informar em número deciaml o percentual do Gás Natural Importado - GLGNi para o produto GLP. Valores de 0
         * a 100
         *
         * @param gniPercent
         * @return
         */
        public Builder withGniPercent(final String gniPercent) {
            this.gniPercent = gniPercent;
            return this;
        }

        /**
         * Valor de partida (cProdANP=210203001). Deve ser informado neste campo o valor por quilograma sem ICMS
         *
         * @param startingValue
         * @return
         */
        public Builder withStartingValue(final String startingValue) {
            this.startingValue = startingValue;
            return this;
        }

        /**
         * Código de autorização / registro do CODIF. Informar apenas quando a UF utilizar o CODIF (Sistema de Controle do Diferimento do Imposto nas Operações com AEAC - Álcool Etílico Anidro
         * Combustível)
         *
         * @param codifCode
         * @return
         */
        public Builder withCodifCode(final String codifCode) {
            this.codifCode = codifCode;
            return this;
        }

        /**
         * Quantidade de combustível faturada à temperatura ambiente. Informar quando a quantidade faturada informada no campo qCom (I10) tiver sido ajustada para uma temperatura diferente da ambiente
         *
         * @param temperature
         * @return
         */
        public Builder withTemperature(final String temperature) {
            this.temperature = temperature;
            return this;
        }

        /**
         * Sigla da UF de Consumo
         *
         * @param consumerUF
         * @return
         */
        public Builder withConsumerUF(final UF consumerUF) {
            this.consumerUF = consumerUF;
            return this;
        }

        /**
         * @param cide
         * @see FuelCide
         * @return
         */
        public Builder withCide(final FuelCide cide) {
            this.cide = cide;
            return this;
        }

        /**
         * @param closing
         * @see FuelClosing
         * @return
         */
        public Builder withClosing(final FuelClosing closing) {
            this.closing = closing;
            return this;
        }

        public Fuel build() {
            final Fuel entity = new Fuel(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Fuel() {
        this.anpProductCode = null;
        this.anpProductDescription = null;
        this.glpPercent = null;
        this.gnnPercent = null;
        this.gniPercent = null;
        this.startingValue = null;
        this.codifCode = null;
        this.temperature = null;
        this.consumerUF = null;
        this.cide = null;
        this.closing = null;
    }

    public Fuel(final Builder builder) {
        this.anpProductCode = builder.anpProductCode;
        this.anpProductDescription = builder.anpProductDescription;
        this.glpPercent = builder.glpPercent;
        this.gnnPercent = builder.gnnPercent;
        this.gniPercent = builder.gniPercent;
        this.startingValue = builder.startingValue;
        this.codifCode = builder.codifCode;
        this.temperature = builder.temperature;
        this.consumerUF = builder.consumerUF;
        this.cide = builder.cide;
        this.closing = builder.closing;
    }

    public String getAnpProductCode() {
        return anpProductCode;
    }

    public String getAnpProductDescription() {
        return anpProductDescription;
    }

    public String getGlpPercent() {
        return glpPercent;
    }

    public String getGnnPercent() {
        return gnnPercent;
    }

    public String getGniPercent() {
        return gniPercent;
    }

    public String getStartingValue() {
        return startingValue;
    }

    public String getCodifCode() {
        return codifCode;
    }

    public String getTemperature() {
        return temperature;
    }

    public UF getConsumerUF() {
        return consumerUF;
    }

    public FuelCide getCide() {
        return cide;
    }

    public FuelClosing getClosing() {
        return closing;
    }

}
