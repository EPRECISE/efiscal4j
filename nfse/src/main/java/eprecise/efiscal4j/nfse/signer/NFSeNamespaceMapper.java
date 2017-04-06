
package eprecise.efiscal4j.nfse.signer;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;


public class NFSeNamespaceMapper extends NamespacePrefixMapper {

    @Override
    public String getPreferredPrefix(final String namespaceUri, final String suggestion, final boolean requirePrefix) {
        return suggestion;
    }

    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[] { "" };
    }

}
