
package eprecise.efiscal4j.nfe.v400.transport;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Dados dos transportes da NF-e
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeTransport implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modFrete") @NotNull final ShippingModality shippingModality;

    private @XmlElement(name = "transporta") final Conveyor conveyor;

    private @XmlElement(name = "retTransp") final TransportICMSRetention transportICMSRetention;

    private @XmlElement(name = "veicTransp") final Vehicle vehicle;

    private @XmlElement(name = "reboque") @Size(max = 5) final List<Vehicle> towing;

    private @XmlElement(name = "vagao") @Size(min = 1, max = 20) final String wagon;

    private @XmlElement(name = "balsa") @Size(min = 1, max = 20) final String ferry;

    private @XmlElement(name = "vol") @Size(max = 5000) final List<TransportedVolume> transportedVolume;

    public static class Builder {

        private ShippingModality shippingModality;

        private Conveyor conveyor;

        private TransportICMSRetention transportICMSRetention;

        private List<TransportedVolume> transportedVolume;

        private Vehicle vehicle;

        private List<Vehicle> towing;

        private String wagon;

        private String ferry;

        /**
         * @see ShippingModality
         * @param shippingModality
         * @return
         */
        public Builder withShippingModality(final ShippingModality shippingModality) {
            this.shippingModality = shippingModality;
            return this;
        }

        /**
         * @see Conveyor
         * @param conveyor
         * @return
         */
        public Builder withConveyor(final Conveyor conveyor) {
            this.conveyor = conveyor;
            return this;
        }

        /**
         * @see TransportICMSRetention
         * @param transportICMSRetention
         * @return
         */
        public Builder withtransportICMSRetention(final TransportICMSRetention transportICMSRetention) {
            this.transportICMSRetention = transportICMSRetention;
            return this;
        }

        /**
         * @see TransportedVolume
         * @param transportedVolume
         * @return
         */
        public Builder withTransportedVolume(final List<TransportedVolume> transportedVolume) {
            this.transportedVolume = transportedVolume;
            return this;
        }

        /**
         * Veículo do transporte
         * 
         * @see Vehicle
         * @param vehicle
         * @return
         */
        public Builder withVehicle(final Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        /**
         * Reboque/Dolly (v2.0)
         * 
         * @see Vehicle
         * @param towing
         * @return
         */
        public Builder withTowing(final List<Vehicle> towing) {
            this.towing = towing;
            return this;
        }

        /**
         * Identificação do vagão (v2.0)
         * 
         * @param wagon
         * @return
         */
        public Builder withWagon(final String wagon) {
            this.wagon = wagon;
            return this;
        }

        /**
         * Identificação da balsa (v2.0)
         * 
         * @param wagon
         * @return
         */
        public Builder withFerry(final String ferry) {
            this.ferry = ferry;
            return this;
        }

        public NFeTransport build() {
            final NFeTransport entity = new NFeTransport(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeTransport() {
        this.shippingModality = null;
        this.conveyor = null;
        this.transportICMSRetention = null;
        this.transportedVolume = null;
        this.vehicle = null;
        this.towing = null;
        this.wagon = null;
        this.ferry = null;
    }

    public NFeTransport(final Builder builder) {
        this.shippingModality = builder.shippingModality;
        this.conveyor = builder.conveyor;
        this.transportICMSRetention = builder.transportICMSRetention;
        this.transportedVolume = builder.transportedVolume;
        this.vehicle = builder.vehicle;
        this.towing = builder.towing;
        this.wagon = builder.wagon;
        this.ferry = builder.ferry;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public ShippingModality getShippingModality() {
        return shippingModality;
    }

    public Conveyor getConveyor() {
        return conveyor;
    }

    public TransportICMSRetention getTransportICMSRetention() {
        return transportICMSRetention;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<Vehicle> getTowing() {
        return towing;
    }

    public String getWagon() {
        return wagon;
    }

    public String getFerry() {
        return ferry;
    }

    public List<TransportedVolume> getTransportedVolume() {
        return transportedVolume;
    }

}
