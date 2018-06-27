
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

    private @XmlElement(name = "transporta") Conveyor conveyor;

    private @XmlElement(name = "retTransp") TransportICMSRetention transportICMSRetention;

    private @XmlElement(name = "veicTransp") Vehicle vehicle;

    private @XmlElement(name = "reboque") @Size(max = 5) List<Vehicle> towing;

    private @XmlElement(name = "vol") @Size(max = 5000) List<TransportedVolume> transportedVolume;

    public static class Builder {

        private ShippingModality shippingModality;

        private Conveyor conveyor;

        private TransportICMSRetention transportICMSRetention;

        private List<TransportedVolume> transportedVolume;

        private Vehicle vehicle;

        private List<Vehicle> towing;

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

        public NFeTransport build() {
            final NFeTransport entity = new NFeTransport(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeTransport() {
        this.shippingModality = null;
    }

    public NFeTransport(final Builder builder) {
        this.shippingModality = builder.shippingModality;
        this.conveyor = builder.conveyor;
        this.transportICMSRetention = builder.transportICMSRetention;
        this.transportedVolume = builder.transportedVolume;
        this.vehicle = builder.vehicle;
        this.towing = builder.towing;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Conveyor getConveyor() {
        return conveyor;
    }

    public void setConveyor(Conveyor conveyor) {
        this.conveyor = conveyor;
    }

    public TransportICMSRetention getTransportICMSRetention() {
        return transportICMSRetention;
    }

    public void setTransportICMSRetention(TransportICMSRetention transportICMSRetention) {
        this.transportICMSRetention = transportICMSRetention;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Vehicle> getTowing() {
        return towing;
    }

    public void setTowing(List<Vehicle> towing) {
        this.towing = towing;
    }

    public List<TransportedVolume> getTransportedVolume() {
        return transportedVolume;
    }

    public void setTransportedVolume(List<TransportedVolume> transportedVolume) {
        this.transportedVolume = transportedVolume;
    }

    public ShippingModality getShippingModality() {
        return shippingModality;
    }

}
