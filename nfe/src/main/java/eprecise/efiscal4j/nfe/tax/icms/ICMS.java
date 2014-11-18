
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.nfe.tax.MainTax;


@XmlAccessorType(XmlAccessType.FIELD)
public abstract class ICMS extends MainTax {

    /**
     * Tributação pelo ICMS 00 - Tributada integralmente
     */
    public static Class<ICMS00.Builder> CST_00 = ICMS00.Builder.class;

    /**
     * Tributação pelo ICMS 10 - Tributada e com cobrança do ICMS por substituição tributária
     */
    public static Class<ICMS10.Builder> CST_10 = ICMS10.Builder.class;

    /**
     * Tributação pelo ICMS 20 - Com redução de base de cálculo
     */
    public static Class<ICMS20.Builder> CST_20 = ICMS20.Builder.class;

    /**
     * Tributação pelo ICMS 30 - Isenta ou não tributada e com cobrança do ICMS por substituição tributária
     */
    public static Class<ICMS30.Builder> CST_30 = ICMS30.Builder.class;

    /**
     * Tributação pelo ICMS 40 - Isenta
     */
    public static Class<ICMS40.Builder> CST_40 = ICMS40.Builder.class;

    /**
     * Tributação pelo ICMS 41 - Não Tributada
     */
    public static Class<ICMS41.Builder> CST_41 = ICMS41.Builder.class;

    /**
     * Tributação pelo ICMS 50 - Suspensão
     */
    public static Class<ICMS50.Builder> CST_50 = ICMS50.Builder.class;

    /**
     * Tributção pelo ICMS 51 - Diferimento
     * 
     * A exigência do preenchimento das informações do ICMS diferido fica à critério de cada UF.
     */
    public static Class<ICMS51.Builder> CST_51 = ICMS51.Builder.class;

    /**
     * Tributação pelo ICMS 60 - ICMS cobrado anteriormente por substituição tributária
     */
    public static Class<ICMS60.Builder> CST_60 = ICMS60.Builder.class;

    /**
     * Tributação pelo ICMS 70 - Com redução de base de cálculo e cobrança do ICMS por substituição tributária
     */
    public static Class<ICMS70.Builder> CST_70 = ICMS70.Builder.class;

    /**
     * Tributação pelo ICMS 90 - Outras
     */
    public static Class<ICMS90.Builder> CST_90 = ICMS90.Builder.class;

    /**
     * Partilha do ICMS entre a UF de origem e UF de destino ou a UF definida na legislação Operação interestadual para consumidor final com partilha do ICMS devido na operação entre a UF de origem e
     * a UF do destinatário ou ou a UF definida na legislação. (Ex. UF da concessionária de entrega do veículos)
     * 
     * CST 10 - Tributada e com cobrança do ICMS por substituição tributária
     */
    public static Class<ICMSPart10.Builder> PART_CST_10 = ICMSPart10.Builder.class;

    /**
     * Partilha do ICMS entre a UF de origem e UF de destino ou a UF definida na legislação Operação interestadual para consumidor final com partilha do ICMS devido na operação entre a UF de origem e
     * a UF do destinatário ou ou a UF definida na legislação. (Ex. UF da concessionária de entrega do veículos)
     * 
     * CST 90 - Outros.
     */
    public static Class<ICMSPart90.Builder> PART_CST_90 = ICMSPart90.Builder.class;

    /**
     * Grupo de informação do ICMSST devido para a UF de destino, nas operações interestaduais de produtos que tiveram retenção antecipada de ICMS por ST na UF do remetente. Repasse via Substituto
     * Tributário.
     */
    public static Class<ICMSST.Builder> ST_CST_41 = ICMSST.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=101 - Tributada pelo Simples Nacional com permissão de crédito.
     */
    public static Class<ICMSSN101.Builder> CSOSN_101 = ICMSSN101.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=102 - Tributada pelo Simples Nacional sem permissão de crédito.
     */
    public static Class<ICMSSN102.Builder> CSOSN_102 = ICMSSN102.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=103 - Isenção do ICMS no Simples Nacional para faixa de receita bruta.
     */
    public static Class<ICMSSN103.Builder> CSOSN_103 = ICMSSN103.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=300 - Imune.
     */
    public static Class<ICMSSN300.Builder> CSOSN_300 = ICMSSN300.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=400 - Não tributada pelo Simples Nacional
     */
    public static Class<ICMSSN400.Builder> CSOSN_400 = ICMSSN400.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=201 - Tributada pelo Simples Nacional com permissão de crédito e com cobrança do ICMS por Substituição Tributária
     */
    public static Class<ICMSSN201.Builder> CSOSN_201 = ICMSSN201.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=202 - Tributada pelo Simples Nacional sem permissão de crédito e com cobrança do ICMS por Substituição Tributária
     */
    public static Class<ICMSSN202.Builder> CSOSN_202 = ICMSSN202.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=203 - Isenção do ICMS nos Simples Nacional para faixa de receita bruta e com cobrança do ICMS por Substituição Tributária
     */
    public static Class<ICMSSN203.Builder> CSOSN_203 = ICMSSN203.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL,CRT=1 – Simples Nacional e CSOSN=500 - ICMS cobrado anteriormente por substituição tributária (substituído) ou por antecipação
     */
    public static Class<ICMSSN500.Builder> CSOSN_500 = ICMSSN500.Builder.class;

    /**
     * Tributação do ICMS pelo SIMPLES NACIONAL, CRT=1 – Simples Nacional e CSOSN=900 - Outros
     */
    public static Class<ICMSSN900.Builder> CSOSN_900 = ICMSSN900.Builder.class;

    public static class Builder {

        public <T extends ICMSBuilder> T fromCode(Class<T> icmsCode) {
            try {
                return icmsCode.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
