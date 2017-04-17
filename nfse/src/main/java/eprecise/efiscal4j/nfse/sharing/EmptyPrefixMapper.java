
package eprecise.efiscal4j.nfse.sharing;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;


public class EmptyPrefixMapper extends NamespacePrefixMapper {

    @Override
    public String getPreferredPrefix(final String namespaceUri, final String suggestion, final boolean requirePrefix) {
        return "";
    }

}
