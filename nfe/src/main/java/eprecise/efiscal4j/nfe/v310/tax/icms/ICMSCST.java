
package eprecise.efiscal4j.nfe.v310.tax.icms;

import java.io.Serializable;

import eprecise.efiscal4j.commons.properties.PropertiesLoader;


/**
 * 
 * @author Felipe Bueno
 *
 */
public enum ICMSCST implements Serializable {

    CST_00("00"),
    CST_10("10"),
    CST_20("20"),
    CST_30("30"),
    CST_40("40"),
    CST_41("41"),
    CST_50("50"),
    CST_51("51"),
    CST_60("60"),
    CST_70("70"),
    CST_90("90"),
    PART_CST_10("10"),
    PART_CST_90("90"),
    ST_CST_41("41"),
    CSOSN_101("101"),
    CSOSN_102("102"),
    CSOSN_103("103"),
    CSOSN_300("300"),
    CSOSN_400("400"),
    CSOSN_201("201"),
    CSOSN_202("202"),
    CSOSN_203("203"),
    CSOSN_500("500"),
    CSOSN_900("900");

    private static final long serialVersionUID = 1L;

    private final PropertiesLoader icmsCstMap = new PropertiesLoader.Builder().resourceLoader(ICMSCST.class).from("/eprecise/efiscal4j/nfe/v310/icmsCst.properties").create();

    private final String value;

    private ICMSCST(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.icmsCstMap.valueFrom(this.toString());
    }

    public String getCstWithDescription() {
        return this.value + " - " + this.icmsCstMap.valueFrom(this.toString());
    }

    public static ICMSCST findByCode(String code) {
        for (final ICMSCST cst : values()) {
            if (cst.getValue().equals(code)) {
                return cst;
            }
        }
        return null;
    }
}
