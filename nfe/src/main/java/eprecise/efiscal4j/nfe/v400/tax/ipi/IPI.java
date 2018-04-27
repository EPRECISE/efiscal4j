
package eprecise.efiscal4j.nfe.v400.tax.ipi;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import eprecise.efiscal4j.nfe.v400.tax.MainTax;
import eprecise.efiscal4j.nfe.v400.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Dados do IPI
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class IPI extends MainTax implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @see IPI00
     */
    public static Class<IPI00.Builder> CST_00 = IPI00.Builder.class;

    /**
     * @see IPI01
     */
    public static Class<IPI01.Builder> CST_01 = IPI01.Builder.class;

    /**
     * @see IPI02
     */
    public static Class<IPI02.Builder> CST_02 = IPI02.Builder.class;

    /**
     * @see IPI03
     */
    public static Class<IPI03.Builder> CST_03 = IPI03.Builder.class;

    /**
     * @see IPI04
     */
    public static Class<IPI04.Builder> CST_04 = IPI04.Builder.class;

    /**
     * @see IPI05
     */
    public static Class<IPI05.Builder> CST_05 = IPI05.Builder.class;

    /**
     * @see IPI49
     */
    public static Class<IPI49.Builder> CST_49 = IPI49.Builder.class;

    /**
     * @see IPI50
     */
    public static Class<IPI50.Builder> CST_50 = IPI50.Builder.class;

    /**
     * @see IPI51
     */
    public static Class<IPI51.Builder> CST_51 = IPI51.Builder.class;

    /**
     * @see IPI52
     */
    public static Class<IPI52.Builder> CST_52 = IPI52.Builder.class;

    /**
     * @see IPI53
     */
    public static Class<IPI53.Builder> CST_53 = IPI53.Builder.class;

    /**
     * @see IPI54
     */
    public static Class<IPI54.Builder> CST_54 = IPI54.Builder.class;

    /**
     * @see IPI55
     */
    public static Class<IPI55.Builder> CST_55 = IPI55.Builder.class;

    /**
     * @see IPI99
     */
    public static Class<IPI99.Builder> CST_99 = IPI99.Builder.class;

    protected @XmlTransient @Size(min = 1, max = 5) @NFeString String ipiFrameworkClass;

    protected @XmlTransient @NFeCNPJ String producerCNPJ;

    protected @XmlTransient @Size(min = 1, max = 60) @NFeString String ipiSealCode;

    protected @XmlTransient @Pattern(regexp = "[0-9]{1,12}") @NFeString String ipiSealQuantity;

    protected @XmlTransient @Size(min = 1, max = 3) @NotNull @NFeString String legalFramework;

    public static class Builder {

        public <T extends IPIBuilder> T fromCode(Class<T> ipiCode) {
            try {
                return ipiCode.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getIpiFrameworkClass() {
        return this.ipiFrameworkClass;
    }

    public String getProducerCNPJ() {
        return this.producerCNPJ;
    }

    public String getIpiSealCode() {
        return this.ipiSealCode;
    }

    public String getIpiSealQuantity() {
        return this.ipiSealQuantity;
    }

    public String getLegalFramework() {
        return this.legalFramework;
    }

    public abstract String getIpiValue();
}
