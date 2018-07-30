
package eprecise.efiscal4j.nfe.item.tax.icms.cst;

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
                                             PART_CST_10("10", true, false),
                                             PART_CST_90("90", true, false),
                                             ST_CST_41("41", false, true),
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

    private final PropertiesLoader icmsCstMap = new PropertiesLoader.Builder().resourceLoader(ICMSCST.class).from("/eprecise/efiscal4j/nfe/cst/icmsCst.properties").create();

    private final String value;

    private final boolean part;

    private final boolean st;

    private ICMSCST(final String value) {
        this(value, false, false);

    }

    private ICMSCST(final String value, final boolean part, final boolean st) {
        this.value = value;
        this.part = part;
        this.st = st;
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

    public boolean isPart() {
        return this.part;
    }

    public boolean isSt() {
        return this.st;
    }

    public static ICMSCST findByCode(final String code, final boolean isPart, final boolean isSt) {
        for (final ICMSCST cst : values()) {
            if (cst.getValue().equals(code) && (isPart == cst.isPart()) && (isSt == cst.isSt())) {
                return cst;
            }
        }
        return null;
    }

}
