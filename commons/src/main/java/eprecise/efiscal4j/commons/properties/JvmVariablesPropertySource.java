/**
 * 
 */

package eprecise.efiscal4j.commons.properties;

/**
 * @author Clécius J. Martinkoski
 * 
 */
public class JvmVariablesPropertySource implements PropertySource {

    /*
     * (non-Javadoc)
     * 
     * @see eprecise.sgv.server.core.infra.properties.Expander.PropertySource#getProperty(java.lang.String)
     */
    @Override
    public String getProperty(String key) {
        return System.getProperty(key);
    }

}
