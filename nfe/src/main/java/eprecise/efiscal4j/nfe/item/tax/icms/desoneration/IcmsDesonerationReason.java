
package eprecise.efiscal4j.nfe.item.tax.icms.desoneration;

import java.io.Serializable;


/**
 * Motivo da desoneração do ICMS
 *
 * @author Fernando Glizt
 *
 */

public enum IcmsDesonerationReason implements Serializable {
    //@formatter:off
    TAXI("1", "Táxi"),
    PRODUTOR_AGROPECUARIO("3", "Produtor agropecuário"),
    FROTISTA_LOCADORA("4", "Frotista/Locadora"),
    DIPLOMATA_CONSULAR("5", "Diplomático/Consular"),
    UTILITARIO_MOTOCICLETAS_AREA_LIVRE_COMERCIO("6",
            "Utilitários e Motocicletas da Amazônia Ocidental e Áreas de Livre Comércio (Resolução 714/88 e 790/94 – CONTRAN e suas alterações)"),
    SUFRAMA("7", "SUFRAMA"),
    VENDA_A_ORGAO_PUBLICO("8", "Venda a órgão Público"),
    OUTROS("9", "Outros"),
    DEFICIENTE_CONDUTOR("10", "Deficiente Condutor"),
    DEFICIENTE_NAO_CONDUTOR("11", "Deficiente não condutor"),
    FOMENTO_AGROPECUARIO("12", "Fomento agropecuário"),
    OLIMPIADAS_RIO_2016("16", "Olimpíadas Rio 2016");
    //@formatter:on
    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private IcmsDesonerationReason(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getICMSDesonerationReasonWithDescription() {
        return this.value + " - " + this.description;
    }

    public static IcmsDesonerationReason findByCode(String code) {
        for (final IcmsDesonerationReason icmsDesonerationReason : IcmsDesonerationReason.values()) {
            if (icmsDesonerationReason.getValue().equals(code)) {
                return icmsDesonerationReason;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
