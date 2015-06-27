
package eprecise.efiscal4j.nfe.sharing.statussearch;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.sun.xml.ws.developer.UsesJAXBContext;

import eprecise.efiscal4j.commons.domain.transmission.FiscalDocumentBody;
import eprecise.efiscal4j.nfe.transmission.NFeHeader;


@WebService(targetNamespace = NfeStatusServico3.DEFAULT_NAMESPACE, name = "NfeStatusServicoSoap12")
@UsesJAXBContext(value = CustomizedJAXBContextFactory.class)
@XmlSeeAlso({ ObjectFactory.class })
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeStatusServicoSoap12 {

//@formatter:off
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3/nfeStatusServicoNF")
    @WebResult(name = "nfeStatusServicoNFResult", targetNamespace = NfeStatusServico3.DEFAULT_NAMESPACE, partName = "nfeStatusServicoNFResult")    
    public NfeStatusServicoNFResult nfeStatusServicoNF(
            @WebParam(name =  "nfeCabecMsg") NFeHeader nfeCabecMsg, 
            @WebParam(name = "nfeDadosMsg") FiscalDocumentBody nfeDadosMsg);
  //@formatter:on    
}
