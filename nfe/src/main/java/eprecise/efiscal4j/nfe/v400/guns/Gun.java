
package eprecise.efiscal4j.nfe.v400.guns;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Grupo do detalhamento de Armamentos
 *
 * @author Fernando C Glizt
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Gun implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "tpArma") @NotNull final GunType gunType;

    private @XmlElement(name = "nSerie") @NotNull @Size(min = 1, max = 15) @NFeString final String gunSerialNumber;

    private @XmlElement(name = "nCano") @NotNull @Size(min = 1, max = 15) @NFeString final String gunBarrelSerialNumber;

    private @XmlElement(name = "descr") @NotNull @Size(min = 1, max = 256) @NFeString final String gunDescription;

    public static class Builder {

        private GunType gunType;

        private String gunSerialNumber;

        private String gunBarrelSerialNumber;

        private String gunDescription;

        /**
         * @param gunType
         * @see GunType
         * @return
         */
        public Builder withGunType(final GunType gunType) {
            this.gunType = gunType;
            return this;
        }

        /**
         * Número de série da arma
         *
         * @param gunSerialNumber
         * @return
         */
        public Builder withGunSerialNumber(final String gunSerialNumber) {
            this.gunSerialNumber = gunSerialNumber;
            return this;
        }

        /**
         * Número de série do cano
         *
         * @param gunBarrelSerialNumber
         * @return
         */
        public Builder withGunBarrelSerialNumber(final String gunBarrelSerialNumber) {
            this.gunBarrelSerialNumber = gunBarrelSerialNumber;
            return this;
        }

        /**
         * Número de série do cano
         *
         * @param gunDescription
         * @return
         */
        public Builder withGunDescription(final String gunDescription) {
            this.gunDescription = gunDescription;
            return this;
        }

        public Gun build() {
            final Gun entity = new Gun(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Gun() {
        this.gunType = null;
        this.gunSerialNumber = null;
        this.gunBarrelSerialNumber = null;
        this.gunDescription = null;
    }

    public Gun(final Builder builder) {
        this.gunType = builder.gunType;
        this.gunSerialNumber = builder.gunSerialNumber;
        this.gunBarrelSerialNumber = builder.gunBarrelSerialNumber;
        this.gunDescription = builder.gunDescription;
    }

    public GunType getGunType() {
        return gunType;
    }

    public String getGunSerialNumber() {
        return gunSerialNumber;
    }

    public String getGunBarrelSerialNumber() {
        return gunBarrelSerialNumber;
    }

    public String getGunDescription() {
        return gunDescription;
    }

}
