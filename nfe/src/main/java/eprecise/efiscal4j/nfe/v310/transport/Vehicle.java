
package eprecise.efiscal4j.nfe.v310.transport;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeLicensePlate;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Dados dos veículos da NF-e
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "placa") @NotNull @NFeString @NFeLicensePlate final String licensePlate;

    private @XmlElement(name = "UF") @NotNull final String uf;

    private @XmlElement(name = "RNTC") @Size(max = 20) @NFeString final String rntc;

    public static class Builder {

        private String licensePlate;

        private UF uf;

        private String rntc;

        /**
         * Placa do veículo (NT2011/004)
         *
         * @param licensePlate
         * @return
         */
        public Builder withLicensePlate(final String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        /**
         * @see UF
         * @param uf
         * @return
         */
        public Builder withUF(final UF uf) {
            this.uf = uf;
            return this;
        }

        /**
         * Registro Nacional de Transportador de Carga (ANTT)
         *
         * @param rntc
         * @return
         */
        public Builder withRntc(final String rntc) {
            this.rntc = rntc;
            return this;
        }

        public Vehicle build() {
            final Vehicle entity = new Vehicle(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Vehicle() {
        this.licensePlate = null;
        this.uf = null;
        this.rntc = null;
    }

    public Vehicle(final Builder builder) {
        this.licensePlate = builder.licensePlate;
        this.uf = Optional.ofNullable(builder.uf).map(uf -> uf.getAcronym()).orElse(null);
        this.rntc = builder.rntc;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public UF getUf() {
        return UF.findByAcronym(this.uf);
    }

    public String getRntc() {
        return this.rntc;
    }

}
