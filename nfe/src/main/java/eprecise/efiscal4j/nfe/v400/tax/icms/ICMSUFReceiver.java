
package eprecise.efiscal4j.nfe.v400.tax.icms;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.tax.MainTax;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Grupo a ser informado nas vendas interestarduais para consumidor final, não contribuinte de ICMS
 * 
 * @see ICMS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSUFReceiver extends MainTax implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBCUFDest") @NFeDecimal1302 @NotNull final String receiverUfBcValue;

    private @XmlElement(name = "pFCPUFDest") @NFeDecimal0302a04 @NotNull final String receiverUfFCPPercentual;

    private @XmlElement(name = "pICMSUFDest") @NFeDecimal0302a04 @NotNull final String receiverUfIcmsAliquot;

    private @XmlElement(name = "pICMSInter") @NotNull final InterstateICMSUFAliquot interstateIcmsUfAliquot;

    private @XmlElement(name = "pICMSInterPart") @NFeDecimal0302a04 @NotNull final String receiverUfSharePercentual;

    private @XmlElement(name = "vFCPUFDest") @NFeDecimal1302 @NotNull final String receiverUfFCPValue;

    private @XmlElement(name = "vICMSUFDest") @NFeDecimal1302 @NotNull final String receiverUfIcmsShareValue;

    private @XmlElement(name = "vICMSUFRemet") @NFeDecimal1302 @NotNull final String emitterUfIcmsShareValue;

    public static class Builder {

        private String receiverUfBcValue;

        private String receiverUfFCPPercentual;

        private String receiverUfIcmsAliquot;

        private InterstateICMSUFAliquot interstateIcmsUfAliquot;

        private String receiverUfSharePercentual;

        private String receiverUfFCPValue;

        private String receiverUfIcmsShareValue;

        private String emitterUfIcmsShareValue;

        /**
         * Valor da Base de Cálculo do ICMS na UF do destinatário.
         * 
         * @param receiverUfBcValue
         * @return
         */
        public Builder withReceiverUfBcValue(String receiverUfBcValue) {
            this.receiverUfBcValue = receiverUfBcValue;
            return this;
        }

        /**
         * Percentual adicional inserido na alíquota interna da UF de destino, relativo ao Fundo de Combate à Pobreza (FCP) naquela UF.
         * 
         * @param receiverUfFCPPercentual
         * @return
         */
        public Builder withReceiverUfFCPPercentual(String receiverUfFCPPercentual) {
            this.receiverUfFCPPercentual = receiverUfFCPPercentual;
            return this;
        }

        /**
         * Alíquota adotada nas operações internas na UF do destinatário para o produto / mercadoria.
         * 
         * @param receiverUfIcmsAliquot
         * @return
         */
        public Builder withReceiverUfIcmsAliquot(String receiverUfIcmsAliquot) {
            this.receiverUfIcmsAliquot = receiverUfIcmsAliquot;
            return this;
        }

        /**
         * 
         * @see InterstateICMSUFAliquot
         * @param interstateIcmsUfAliquot
         * @return
         */
        public Builder withInterstateIcmsUfAliquot(InterstateICMSUFAliquot interstateIcmsUfAliquot) {
            this.interstateIcmsUfAliquot = interstateIcmsUfAliquot;
            return this;
        }

        /**
         * Percentual de partilha para a UF do destinatário: - 40% em 2016; - 60% em 2017; - 80% em 2018; - 100% a partir de 2019.
         * 
         * @param receiverUfSharePercentual
         * @return
         */
        public Builder withReceiverUfSharePercentual(String receiverUfSharePercentual) {
            this.receiverUfSharePercentual = receiverUfSharePercentual;
            return this;
        }

        /**
         * Valor do ICMS relativo ao Fundo de Combate à Pobreza (FCP) da UF de destino.
         * 
         * @param receiverUfFCPValue
         * @return
         */
        public Builder withReceiverUfFCPValue(String receiverUfFCPValue) {
            this.receiverUfFCPValue = receiverUfFCPValue;
            return this;
        }

        /**
         * Valor do ICMS de partilha para a UF do destinatário.
         * 
         * @param receiverUfIcmsShareValue
         * @return
         */
        public Builder withReceiverUfIcmsShareValue(String receiverUfIcmsShareValue) {
            this.receiverUfIcmsShareValue = receiverUfIcmsShareValue;
            return this;
        }

        /**
         * Valor do ICMS de partilha para a UF do remetente. Nota: A partir de 2019, este valor será zero.
         * 
         * @param emitterUfIcmsShareValue
         * @return
         */
        public Builder withEmitterUfIcmsShareValue(String emitterUfIcmsShareValue) {
            this.emitterUfIcmsShareValue = emitterUfIcmsShareValue;
            return this;
        }

        public ICMSUFReceiver build() {
            final ICMSUFReceiver entity = new ICMSUFReceiver(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ICMSUFReceiver() {
        this.receiverUfBcValue = null;
        this.receiverUfFCPPercentual = null;
        this.receiverUfIcmsAliquot = null;
        this.interstateIcmsUfAliquot = null;
        this.receiverUfSharePercentual = null;
        this.receiverUfFCPValue = null;
        this.receiverUfIcmsShareValue = null;
        this.emitterUfIcmsShareValue = null;
    }

    public ICMSUFReceiver(Builder builder) {
        this.receiverUfBcValue = builder.receiverUfBcValue;
        this.receiverUfFCPPercentual = builder.receiverUfFCPPercentual;
        this.receiverUfIcmsAliquot = builder.receiverUfIcmsAliquot;
        this.interstateIcmsUfAliquot = builder.interstateIcmsUfAliquot;
        this.receiverUfSharePercentual = builder.receiverUfSharePercentual;
        this.receiverUfFCPValue = builder.receiverUfFCPValue;
        this.receiverUfIcmsShareValue = builder.receiverUfIcmsShareValue;
        this.emitterUfIcmsShareValue = builder.emitterUfIcmsShareValue;
    }

    public String getReceiverUfBcValue() {
        return this.receiverUfBcValue;
    }

    public String getReceiverUfFCPPercentual() {
        return this.receiverUfFCPPercentual;
    }

    public String getReceiverUfIcmsAliquot() {
        return this.receiverUfIcmsAliquot;
    }

    public InterstateICMSUFAliquot getInterstateIcmsUfAliquot() {
        return this.interstateIcmsUfAliquot;
    }

    public String getReceiverUfSharePercentual() {
        return this.receiverUfSharePercentual;
    }

    public String getReceiverUfFCPValue() {
        return this.receiverUfFCPValue;
    }

    public String getReceiverUfIcmsShareValue() {
        return this.receiverUfIcmsShareValue;
    }

    public String getEmitterUfIcmsShareValue() {
        return this.emitterUfIcmsShareValue;
    }

}
