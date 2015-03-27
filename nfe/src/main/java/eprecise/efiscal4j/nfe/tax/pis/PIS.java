
package eprecise.efiscal4j.nfe.tax.pis;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.nfe.tax.FederalTax;


/**
 * Dados do PIS
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class PIS extends FederalTax implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @see PIS01
     */
    public static Class<PIS01.Builder> CST_01 = PIS01.Builder.class;

    /**
     * @see PIS02
     */
    public static Class<PIS02.Builder> CST_02 = PIS02.Builder.class;

    /**
     * @see PIS03
     */
    public static Class<PIS03.Builder> CST_03 = PIS03.Builder.class;

    /**
     * @see PIS04
     */
    public static Class<PIS04.Builder> CST_04 = PIS04.Builder.class;

    /**
     * @see PIS05
     */
    public static Class<PIS05.Builder> CST_05 = PIS05.Builder.class;

    /**
     * @see PIS06
     */
    public static Class<PIS06.Builder> CST_06 = PIS06.Builder.class;

    /**
     * @see PIS07
     */
    public static Class<PIS07.Builder> CST_07 = PIS07.Builder.class;

    /**
     * @see PIS08
     */
    public static Class<PIS08.Builder> CST_08 = PIS08.Builder.class;

    /**
     * @see PIS09
     */
    public static Class<PIS09.Builder> CST_09 = PIS09.Builder.class;

    /**
     * @see PIS49
     */
    public static Class<PIS49.Builder> CST_49 = PIS49.Builder.class;

    /**
     * @see PIS50
     */
    public static Class<PIS50.Builder> CST_50 = PIS50.Builder.class;

    /**
     * @see PIS51
     */
    public static Class<PIS51.Builder> CST_51 = PIS51.Builder.class;

    /**
     * @see PIS52
     */
    public static Class<PIS52.Builder> CST_52 = PIS52.Builder.class;

    /**
     * @see PIS53
     */
    public static Class<PIS53.Builder> CST_53 = PIS53.Builder.class;

    /**
     * @see PIS54
     */
    public static Class<PIS54.Builder> CST_54 = PIS54.Builder.class;

    /**
     * @see PIS55
     */
    public static Class<PIS55.Builder> CST_55 = PIS55.Builder.class;

    /**
     * @see PIS56
     */
    public static Class<PIS56.Builder> CST_56 = PIS56.Builder.class;

    /**
     * @see PIS60
     */
    public static Class<PIS60.Builder> CST_60 = PIS60.Builder.class;

    /**
     * @see PIS61
     */
    public static Class<PIS61.Builder> CST_61 = PIS61.Builder.class;

    /**
     * @see PIS62
     */
    public static Class<PIS62.Builder> CST_62 = PIS62.Builder.class;

    /**
     * @see PIS63
     */
    public static Class<PIS63.Builder> CST_63 = PIS63.Builder.class;

    /**
     * @see PIS64
     */
    public static Class<PIS64.Builder> CST_64 = PIS64.Builder.class;

    /**
     * @see PIS65
     */
    public static Class<PIS65.Builder> CST_65 = PIS65.Builder.class;

    /**
     * @see PIS66
     */
    public static Class<PIS66.Builder> CST_66 = PIS66.Builder.class;

    /**
     * @see PIS67
     */
    public static Class<PIS67.Builder> CST_67 = PIS67.Builder.class;

    /**
     * @see PIS70
     */
    public static Class<PIS70.Builder> CST_70 = PIS70.Builder.class;

    /**
     * @see PIS71
     */
    public static Class<PIS71.Builder> CST_71 = PIS71.Builder.class;

    /**
     * @see PIS72
     */
    public static Class<PIS72.Builder> CST_72 = PIS72.Builder.class;

    /**
     * @see PIS73
     */
    public static Class<PIS73.Builder> CST_73 = PIS73.Builder.class;

    /**
     * @see PIS74
     */
    public static Class<PIS74.Builder> CST_74 = PIS74.Builder.class;

    /**
     * @see PIS75
     */
    public static Class<PIS75.Builder> CST_75 = PIS75.Builder.class;

    /**
     * @see PIS98
     */
    public static Class<PIS98.Builder> CST_98 = PIS98.Builder.class;

    /**
     * @see PIS99
     */
    public static Class<PIS99.Builder> CST_99 = PIS99.Builder.class;

    public static class Builder {

        public <T extends PISBuilder> T fromCode(Class<T> pisCode) {
            try {
                return pisCode.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract String getPisValue();
}
