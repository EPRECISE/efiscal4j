
package eprecise.efiscal4j.signer;

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
public abstract class Assignable {

    public @XmlElement(name = "Signature") SignatureType signature;

    public abstract String getAsXml();

    public abstract Assignable getAsEntity(String xml);

    public Assignable getAsEntity(final InputStream xml) throws IOException {
        return this.getAsEntity(IOUtils.toString(xml));
    }

    public Assignable getAsEntity(final URL xml) throws IOException {
        return this.getAsEntity(IOUtils.toString(xml.openStream()));
    }

    public Assignable getAsEntity(final File xml) throws FileNotFoundException, IOException {
        return this.getAsEntity(IOUtils.toString(new FileInputStream(xml)));
    }

    public SignatureType getSignature() {
        return signature;
    }

    public abstract String getRootTagName();

    public abstract String getAssignableTagName();

    public abstract String getIdAttributeTagName();

}
