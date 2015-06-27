
package eprecise.efiscal4j.nfe.sharing.statussearch;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Validator;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;

import com.sun.xml.bind.api.Bridge;
import com.sun.xml.bind.api.BridgeContext;
import com.sun.xml.bind.api.JAXBRIContext;
import com.sun.xml.bind.api.RawAccessor;
import com.sun.xml.bind.api.TypeReference;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;

import eprecise.efiscal4j.commons.xml.PreferredMapper;


public class CustomizedContext extends JAXBRIContext {

    private final JAXBRIContext delegate;

    public CustomizedContext(final JAXBRIContext createContext) {
        this.delegate = createContext;
    }

    @Override
    public Bridge createBridge(final TypeReference arg0) {
        return new CustomizedBridge((JAXBContextImpl) this.delegate, this.delegate.createBridge(arg0));
    }

    @Override
    public boolean hasSwaRef() {
        return this.delegate.hasSwaRef();
    }

    @Override
    public QName getElementName(Object o) throws JAXBException {
        return this.delegate.getElementName(o);
    }

    @Override
    public QName getElementName(Class o) throws JAXBException {
        return this.delegate.getElementName(o);
    }

    @Override
    public BridgeContext createBridgeContext() {
        return this.delegate.createBridgeContext();
    }

    @Override
    public <B, V> RawAccessor<B, V> getElementPropertyAccessor(Class<B> wrapperBean, String nsUri, String localName) throws JAXBException {
        return this.delegate.getElementPropertyAccessor(wrapperBean, nsUri, localName);
    }

    @Override
    public List<String> getKnownNamespaceURIs() {
        return this.delegate.getKnownNamespaceURIs();
    }

    @Override
    public void generateSchema(SchemaOutputResolver outputResolver) throws IOException {
        this.delegate.generateSchema(outputResolver);
    }

    @Override
    public QName getTypeName(TypeReference tr) {
        return this.delegate.getTypeName(tr);
    }

    @Override
    public String getBuildId() {
        return this.delegate.getBuildId();
    }

    @Override
    public void generateEpisode(Result output) {
        this.delegate.generateEpisode(output);
    }

    @Override
    public RuntimeTypeInfoSet getRuntimeTypeInfoSet() {
        return this.delegate.getRuntimeTypeInfoSet();
    }

    @Override
    public Unmarshaller createUnmarshaller() throws JAXBException {
        return this.delegate.createUnmarshaller();
    }

    @Override
    public Marshaller createMarshaller() throws JAXBException {
        final Marshaller marshaller = this.delegate.createMarshaller();
        marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new PreferredMapper());
        return marshaller;
    }

    @Override
    public Validator createValidator() throws JAXBException {
        return this.delegate.createValidator();
    }
}
