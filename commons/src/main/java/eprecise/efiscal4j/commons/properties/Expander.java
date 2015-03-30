
package eprecise.efiscal4j.commons.properties;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Classe responsável por analisar uma propriedade e expandir valor de variáveis contidas nesta
 * 
 * @author Clécius J. Martinkoski
 * 
 */
public class Expander implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");

    /**
     * Expande a variáveis da forma "${variableName}" a partir de uma String, Usando um PropertySource para buscar a fonte do parâmetro.
     */
    public String expand(final String s, final PropertySource propertySource) {
        if (s == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        final Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            final String variableName = matcher.group(1);
            String value = null;
            for (String var : variableName.split(":")) {
                if (var.matches("'.*'")) {
                    value = var.replaceAll("'", "");
                } else {
                    value = propertySource.getProperty(var);
                }
                if (value != null)
                    break;
            }
            if (value != null) {
                matcher.appendReplacement(sb, value);
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
