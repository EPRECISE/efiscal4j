
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Detalhe do Evento - Carta de Correção
 * 
 * @see EventDetail
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDetailCCe extends EventDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "xCorrecao") @NotNull @Size(min = 15, max = 1000) @NFeString final String correction;

    private @XmlElement(
            name = "xCondUso") @NotNull final String termsOfUse = "A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.";

    public static class Builder {

        private String correction;

        /**
         * Correção a ser considerada
         * 
         * @param correction
         * @return
         */
        public Builder withCorrection(String correction) {
            this.correction = correction;
            return this;
        }

        public EventDetailCCe build() {
            final EventDetailCCe entity = new EventDetailCCe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventDetailCCe() {
        super();
        this.correction = null;
    }

    public EventDetailCCe(Builder builder) {
        super(EventType.CCE.getDescription());
        this.correction = builder.correction;
    }

    @Override
    public FiscalDocumentVersion getVersion() {
        return super.getVersion();
    }

    @Override
    public String getEventDescription() {
        return super.getEventDescription();
    }

    public String getCorrection() {
        return this.correction;
    }

    public String getTermsOfUse() {
        return this.termsOfUse;
    }

}
