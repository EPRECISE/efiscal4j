
package eprecise.efiscal4j.nfe.item;

import java.util.Optional;
import java.util.stream.Stream;

public enum DefaultUnity implements Unity{
                   AMPOLA("AMPOLA"),
                   BALDE("BALDE"),
                   BANDEJA("BANDEJ"),
                   BARRA("BARRA"),
                   BISNAGA("BISNAG"),
                   BLOCO("BLOCO"),
                   BOBINA("BOBINA"),
                   BOMBONA("BOMB"),
                   CAPSULA("CAPS"),
                   CARTELA("CART"),
                   CENTO("CENTO"),
                   CONJUNTO("CJ"),
                   CENTIMETRO("CM"),
                   CENTIMETRO_QUADRADO("CM2"),
                   CAIXA("CX"),
                   CAIXA_COM_2_UNIDADES("CX2"),
                   CAIXA_COM_3_UNIDADES("CX3"),
                   CAIXA_COM_5_UNIDADES("CX5"),
                   CAIXA_COM_10_UNIDADES("CX10"),
                   CAIXA_COM_15_UNIDADES("CX15"),
                   CAIXA_COM_20_UNIDADES("CX20"),
                   CAIXA_COM_25_UNIDADES("CX25"),
                   CAIXA_COM_50_UNIDADES("CX50"),
                   CAIXA_COM_100_UNIDADES("CX100"),
                   DISPLAY("DISP"),
                   DUZIA("DUZIA"),
                   EMBALAGEM("EMBAL"),
                   FARDO("FARDO"),
                   FOLHA("FOLHA"),
                   FRASCO("FRASCO"),
                   GALAO("GALAO", "Galão"),
                   GARRAFA("GF"),
                   GRAMAS("GRAMAS"),
                   HORAS("H"),
                   JOGO("JOGO"),
                   QUILOGRAMAS("KG"),
                   KIT("KIT"),
                   LATA("LATA"),
                   LITROS("LITRO"),
                   METROS("M"),
                   METROS_QUADRADOS("M2"),
                   METRO_CUBICO("M3", "Metro Cúbico"),
                   MILHEIRO("MILHEI"),
                   MILILITRO("ML"),
                   MEGAWATT_HORA("MWH"),
                   PACOTE("PACOTE"),
                   PALETE("PALETE"),
                   PARES("PARES"),
                   PECA("PC", "Peça"),
                   POTE("POTE"),
                   QUILATE("K"),
                   RESMA("RESMA"),
                   ROLO("ROLO"),
                   SACO("SACO"),
                   SACOLA("SACOLA"),
                   TAMBOR("TAMBOR"),
                   TANQUE("TANQUE"),
                   TONELADA("TON"),
                   TUBO("TUBO"),
                   UNIDADE("UNID"),
                   VASILHAME("VASIL"),
                   VIDRO("VIDRO");

    private final String acronym;

    private final String fullName;

    private DefaultUnity(final String acronym) {
        this(acronym, null);
    }

    private DefaultUnity(final String acronym, final String fullName) {
        this.acronym = acronym;
        this.fullName = fullName != null ? fullName : formatToLegible(this.name());
    }

    public String getAcronym() {
        return this.acronym;
    }

    public String getFullName() {
        return this.fullName;
    }

    public static String formatToLegible(final String strName) {
        final StringBuilder result = new StringBuilder();
        for (String namePart : strName.split("_")) {
            if (namePart.length() > 2) {
                result.append(String.valueOf(namePart.charAt(0)).toUpperCase()).append(namePart.substring(1).toLowerCase());
            } else {
                result.append(namePart);
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
    
    public static Optional<DefaultUnity> findByAcronym(String acronym) {
        return Stream.of(DefaultUnity.values()).filter(e -> e.getAcronym().equalsIgnoreCase(acronym)).findFirst();
    }

}
