
package eprecise.efiscal4j.signer.defaults;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.io.IOUtils;

import eprecise.efiscal4j.signer.domain.SignatureType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlTransient
public abstract class DefaultAssignable {

    public @XmlElement(name = "Signature") SignatureType signature;

    public abstract String getAsXml();

    public abstract DefaultAssignable getAsEntity(String xml);

    public DefaultAssignable getAsEntity(final InputStream xml) throws IOException {
        return this.getAsEntity(IOUtils.toString(xml));
    }

    public DefaultAssignable getAsEntity(final URL xml) throws IOException {
        return this.getAsEntity(IOUtils.toString(xml.openStream()));
    }

    public DefaultAssignable getAsEntity(final File xml) throws FileNotFoundException, IOException {
        return this.getAsEntity(IOUtils.toString(new FileInputStream(xml)));
    }

    public SignatureType getSignature() {
        return signature;
    }

    public abstract String getRootTagName();

    public abstract String getAssignableTagName();

    public abstract String getIdAttributeTagName();

}
