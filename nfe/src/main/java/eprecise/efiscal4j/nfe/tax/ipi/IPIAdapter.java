
package eprecise.efiscal4j.nfe.tax.ipi;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfe.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * 
 * @author Felipe Bueno
 * 
 */
public class IPIAdapter extends XmlAdapter<IPIAdapter.AdaptedIPI, IPI> {

    @Override
    public IPI unmarshal(AdaptedIPI v) throws Exception {
        v.getIpi().ipiFrameworkClass = v.ipiFrameworkClass;
        v.getIpi().ipiSealCode = v.ipiSealCode;
        v.getIpi().ipiSealQuantity = v.ipiSealQuantity;
        v.getIpi().legalFramework = v.legalFramework;
        v.getIpi().producerCNPJ = v.producerCNPJ;
        return v.getIpi();
    }

    @Override
    public AdaptedIPI marshal(IPI v) throws Exception {
        return (v == null ? null : new AdaptedIPI(v));
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    protected static class AdaptedIPI {

        @XmlElement(name = "clEnq") @Size(min = 1, max = 5) String ipiFrameworkClass;

        @XmlElement(name = "CNPJProd") @NFeCNPJ String producerCNPJ;

        @XmlElement(name = "cSelo") @Size(min = 1, max = 60) @NFeString String ipiSealCode;

        @XmlElement(name = "qSelo") @Pattern(regexp = "[0-9]{1,12}") @NFeString String ipiSealQuantity;

        @XmlElement(name = "cEnq") @Size(min = 1, max = 3) @NotNull @NFeString final String legalFramework;

        // @formatter:off
        @XmlElements(value = { 
                @XmlElement(name = "IPITrib", type = IPI00.class),
                @XmlElement(name = "IPITrib", type = IPI49.class),
                @XmlElement(name = "IPITrib", type = IPI50.class),
                @XmlElement(name = "IPITrib", type = IPI99.class),
                @XmlElement(name = "IPINT", type = IPI01.class),
                @XmlElement(name = "IPINT", type = IPI02.class),
                @XmlElement(name = "IPINT", type = IPI03.class),
                @XmlElement(name = "IPINT", type = IPI04.class),
                @XmlElement(name = "IPINT", type = IPI05.class),
                @XmlElement(name = "IPINT", type = IPI51.class),
                @XmlElement(name = "IPINT", type = IPI52.class),
                @XmlElement(name = "IPINT", type = IPI53.class),
                @XmlElement(name = "IPINT", type = IPI54.class),
                @XmlElement(name = "IPINT", type = IPI55.class),                
                })                    
        private final IPI ipi;
        // @formatter:on       

        public AdaptedIPI() {
            this.ipi = null;
            this.legalFramework = null;
        }

        public AdaptedIPI(IPI ipi) {
            this.ipi = ipi;
            this.ipiFrameworkClass = ipi.getIpiFrameworkClass();
            this.producerCNPJ = ipi.getProducerCNPJ();
            this.ipiSealCode = ipi.getIpiSealCode();
            this.ipiSealQuantity = ipi.getIpiSealQuantity();
            this.legalFramework = ipi.getLegalFramework();
        }

        public IPI getIpi() {
            return this.ipi;
        }
    }
}
