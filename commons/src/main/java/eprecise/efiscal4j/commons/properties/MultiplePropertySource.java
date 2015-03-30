/**
 * 
 */

package eprecise.efiscal4j.commons.properties;

/**
 * @author Clécius J. Martinkoski
 * 
 */
public class MultiplePropertySource implements PropertySource {

    private final PropertySource[] sources;

    public MultiplePropertySource(PropertySource... sources) {
        this.sources = sources;
    }

    /*
     * (non-Javadoc)
     * 
     * @see eprecise.sgv.server.core.infra.properties.Expander.PropertySource#getProperty(java.lang.String)
     */
    @Override
    public String getProperty(String key) {
        for (PropertySource source : sources) {
            String property = source.getProperty(key);
            if (property != null)
                return property;
        }
        return null;
    }

}
