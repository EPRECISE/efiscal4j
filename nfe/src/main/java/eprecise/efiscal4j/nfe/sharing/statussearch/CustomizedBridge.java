
package eprecise.efiscal4j.nfe.sharing.statussearch;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;

import com.sun.xml.bind.api.Bridge;
import com.sun.xml.bind.api.TypeReference;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;

import eprecise.efiscal4j.commons.xml.PreferredMapper;


public class CustomizedBridge extends Bridge {

    private final Bridge delegate;

    protected CustomizedBridge(final JAXBContextImpl context, final Bridge delegate) {
        super(context);
        this.delegate = delegate;
    }

    private void setNamespacePrefixMapper(Marshaller m) throws PropertyException {
        m.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new PreferredMapper());
    }

    @Override
    public void marshal(final Marshaller m, final Object object, final ContentHandler contentHandler) throws JAXBException {
        this.setNamespacePrefixMapper(m);
        this.delegate.marshal(m, object, contentHandler);
    }

    @Override
    public void marshal(Marshaller m, Object object, XMLStreamWriter output) throws JAXBException {
        this.setNamespacePrefixMapper(m);
        this.delegate.marshal(m, object, output);
    }

    @Override
    public void marshal(Marshaller m, Object object, OutputStream output, NamespaceContext nsContext) throws JAXBException {
        this.setNamespacePrefixMapper(m);
        this.delegate.marshal(m, object, output, nsContext);
    }

    @Override
    public void marshal(Marshaller m, Object object, Node output) throws JAXBException {
        this.setNamespacePrefixMapper(m);
        this.delegate.marshal(m, object, output);
    }

    @Override
    public void marshal(Marshaller m, Object object, Result result) throws JAXBException {
        this.setNamespacePrefixMapper(m);
        this.delegate.marshal(m, object, result);
    }

    @Override
    public Object unmarshal(Unmarshaller u, XMLStreamReader in) throws JAXBException {
        return this.delegate.unmarshal(u, in);
    }

    @Override
    public Object unmarshal(Unmarshaller u, Source in) throws JAXBException {
        return this.delegate.unmarshal(u, in);
    }

    @Override
    public Object unmarshal(Unmarshaller u, InputStream in) throws JAXBException {
        return this.delegate.unmarshal(u, in);
    }

    @Override
    public Object unmarshal(Unmarshaller context, Node n) throws JAXBException {
        return this.delegate.unmarshal(context, n);
    }

    @Override
    public TypeReference getTypeReference() {
        return this.delegate.getTypeReference();
    }
}
