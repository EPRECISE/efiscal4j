
package eprecise.efiscal4j.nfe.tax.ipi;

import java.io.Serializable;

import eprecise.efiscal4j.commons.properties.PropertiesLoader;


/**
 * 
 * @author Felipe Bueno
 *
 */
public enum IPICST implements Serializable {

    CST_00("00"),
    CST_01("01"),
    CST_02("02"),
    CST_03("03"),
    CST_04("04"),
    CST_05("05"),
    CST_49("49"),
    CST_50("50"),
    CST_51("51"),
    CST_52("52"),
    CST_53("53"),
    CST_54("54"),
    CST_55("55"),
    CST_99("99");

    private static final long serialVersionUID = 1L;

    private final PropertiesLoader ipiCstMap = new PropertiesLoader.Builder().resourceLoader(IPICST.class).from("/eprecise/efiscal4j/nfe/ipiCst.properties").create();

    private final String value;

    private IPICST(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.ipiCstMap.valueFrom(this.toString());
    }

    public static IPICST findByCode(String code) {
        for (final IPICST cst : values()) {
            if (cst.getValue().equals(code)) {
                return cst;
            }
        }
        return null;
    }
}
