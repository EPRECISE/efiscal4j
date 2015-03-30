/**
 * 
 */

package eprecise.efiscal4j.commons.properties;

/**
 * 
 * @author Clécius J. Martinkoski
 * 
 */
public class EnvVariablesPropertiesSource implements PropertySource {

    /*
     * (non-Javadoc)
     * 
     * @see eprecise.sgv.server.core.infra.Expander.PropertySource#getProperty(java.lang.String)
     */
    @Override
    public String getProperty(String key) {
        return System.getenv().get(key);
    }

}
