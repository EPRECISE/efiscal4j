
package eprecise.efiscal4j.nfe.sharing.statussearch;

import java.util.List;

import javax.xml.bind.JAXBException;

import com.sun.xml.bind.api.JAXBRIContext;
import com.sun.xml.bind.api.TypeReference;
import com.sun.xml.ws.api.model.SEIModel;
import com.sun.xml.ws.developer.JAXBContextFactory;


public class CustomizedJAXBContextFactory implements JAXBContextFactory {

    @Override
    public JAXBRIContext createJAXBContext(SEIModel sei, List<Class> classesToBind, List<TypeReference> typeReferences) throws JAXBException {

        final JAXBRIContext context = JAXBContextFactory.DEFAULT.createJAXBContext(sei, classesToBind, typeReferences);
        return new CustomizedContext(context);
    }
}
