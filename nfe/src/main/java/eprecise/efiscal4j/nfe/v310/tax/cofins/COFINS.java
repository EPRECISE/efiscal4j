
package eprecise.efiscal4j.nfe.v310.tax.cofins;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.nfe.v310.tax.FederalTax;


/**
 * Dados do COFINS
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class COFINS extends FederalTax implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @see COFINS01
     */
    public static Class<COFINS01.Builder> CST_01 = COFINS01.Builder.class;

    /**
     * @see COFINS02
     */
    public static Class<COFINS02.Builder> CST_02 = COFINS02.Builder.class;

    /**
     * @see COFINS03
     */
    public static Class<COFINS03.Builder> CST_03 = COFINS03.Builder.class;

    /**
     * @see COFINS04
     */
    public static Class<COFINS04.Builder> CST_04 = COFINS04.Builder.class;

    /**
     * @see COFINS05
     */
    public static Class<COFINS05.Builder> CST_05 = COFINS05.Builder.class;

    /**
     * @see COFINS06
     */
    public static Class<COFINS06.Builder> CST_06 = COFINS06.Builder.class;

    /**
     * @see COFINS07
     */
    public static Class<COFINS07.Builder> CST_07 = COFINS07.Builder.class;

    /**
     * @see COFINS08
     */
    public static Class<COFINS08.Builder> CST_08 = COFINS08.Builder.class;

    /**
     * @see COFINS09
     */
    public static Class<COFINS09.Builder> CST_09 = COFINS09.Builder.class;

    /**
     * @see COFINS49
     */
    public static Class<COFINS49.Builder> CST_49 = COFINS49.Builder.class;

    /**
     * @see COFINS50
     */
    public static Class<COFINS50.Builder> CST_50 = COFINS50.Builder.class;

    /**
     * @see COFINS51
     */
    public static Class<COFINS51.Builder> CST_51 = COFINS51.Builder.class;

    /**
     * @see COFINS52
     */
    public static Class<COFINS52.Builder> CST_52 = COFINS52.Builder.class;

    /**
     * @see COFINS53
     */
    public static Class<COFINS53.Builder> CST_53 = COFINS53.Builder.class;

    /**
     * @see COFINS54
     */
    public static Class<COFINS54.Builder> CST_54 = COFINS54.Builder.class;

    /**
     * @see COFINS55
     */
    public static Class<COFINS55.Builder> CST_55 = COFINS55.Builder.class;

    /**
     * @see COFINS56
     */
    public static Class<COFINS56.Builder> CST_56 = COFINS56.Builder.class;

    /**
     * @see COFINS60
     */
    public static Class<COFINS60.Builder> CST_60 = COFINS60.Builder.class;

    /**
     * @see COFINS61
     */
    public static Class<COFINS61.Builder> CST_61 = COFINS61.Builder.class;

    /**
     * @see COFINS62
     */
    public static Class<COFINS62.Builder> CST_62 = COFINS62.Builder.class;

    /**
     * @see COFINS63
     */
    public static Class<COFINS63.Builder> CST_63 = COFINS63.Builder.class;

    /**
     * @see COFINS64
     */
    public static Class<COFINS64.Builder> CST_64 = COFINS64.Builder.class;

    /**
     * @see COFINS65
     */
    public static Class<COFINS65.Builder> CST_65 = COFINS65.Builder.class;

    /**
     * @see COFINS66
     */
    public static Class<COFINS66.Builder> CST_66 = COFINS66.Builder.class;

    /**
     * @see COFINS67
     */
    public static Class<COFINS67.Builder> CST_67 = COFINS67.Builder.class;

    /**
     * @see COFINS70
     */
    public static Class<COFINS70.Builder> CST_70 = COFINS70.Builder.class;

    /**
     * @see COFINS71
     */
    public static Class<COFINS71.Builder> CST_71 = COFINS71.Builder.class;

    /**
     * @see COFINS72
     */
    public static Class<COFINS72.Builder> CST_72 = COFINS72.Builder.class;

    /**
     * @see COFINS73
     */
    public static Class<COFINS73.Builder> CST_73 = COFINS73.Builder.class;

    /**
     * @see COFINS74
     */
    public static Class<COFINS74.Builder> CST_74 = COFINS74.Builder.class;

    /**
     * @see COFINS75
     */
    public static Class<COFINS75.Builder> CST_75 = COFINS75.Builder.class;

    /**
     * @see COFINS98
     */
    public static Class<COFINS98.Builder> CST_98 = COFINS98.Builder.class;

    /**
     * @see COFINS99
     */
    public static Class<COFINS99.Builder> CST_99 = COFINS99.Builder.class;

    public static class Builder {

        public <T extends COFINSBuilder> T fromCode(Class<T> cofinsCode) {
            try {
                return cofinsCode.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract String getCofinsValue();
}
