
package eprecise.efiscal4j.commons.properties;

/**
 * Interface de fonte de propriedades
 * 
 * @author Clécius J. Martinkoski
 * 
 */
public interface PropertySource {

    /**
     * Retorna o valor de uma determinada chave
     * 
     * @param key
     *            - chave para buscar o valor
     * @return o valor da chave parametrizada
     */
    String getProperty(String key);
}
