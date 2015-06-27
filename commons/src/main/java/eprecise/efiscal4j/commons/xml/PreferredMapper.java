
package eprecise.efiscal4j.commons.xml;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;


public class PreferredMapper extends NamespacePrefixMapper {

    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        return "" + namespaceUri;
    }

}
