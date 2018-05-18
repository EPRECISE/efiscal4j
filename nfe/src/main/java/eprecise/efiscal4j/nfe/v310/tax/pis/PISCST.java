
package eprecise.efiscal4j.nfe.v310.tax.pis;

import java.io.Serializable;

import eprecise.efiscal4j.commons.properties.PropertiesLoader;


/**
 * 
 * @author Felipe Bueno
 *
 */
public enum PISCST implements Serializable {

    CST_01("01"),
    CST_02("02"),
    CST_03("03"),
    CST_04("04"),
    CST_05("05"),
    CST_06("06"),
    CST_07("07"),
    CST_08("08"),
    CST_09("09"),

    CST_49("49"),
    CST_50("50"),
    CST_51("51"),
    CST_52("52"),
    CST_53("53"),
    CST_54("54"),
    CST_55("55"),
    CST_56("56"),

    CST_60("60"),
    CST_61("61"),
    CST_62("62"),
    CST_63("63"),
    CST_64("64"),
    CST_65("65"),
    CST_66("66"),
    CST_67("67"),

    CST_70("70"),
    CST_71("71"),
    CST_72("72"),
    CST_73("73"),
    CST_74("74"),
    CST_75("75"),

    CST_98("98"),
    CST_99("99");

    private static final long serialVersionUID = 1L;

    private final PropertiesLoader pisCstMap = new PropertiesLoader.Builder().resourceLoader(PISCST.class).from("/eprecise/efiscal4j/nfe/v310/pisCst.properties").create();

    private final String value;

    private PISCST(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.pisCstMap.valueFrom(this.toString());
    }

    public String getCstWithDescription() {
        return this.value + " - " + this.pisCstMap.valueFrom(this.toString());
    }

    public static PISCST findByCode(String code) {
        for (final PISCST cst : values()) {
            if (cst.getValue().equals(code)) {
                return cst;
            }
        }
        return null;
    }
}
