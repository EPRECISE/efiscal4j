
package eprecise.efiscal4j.nfe.transmission;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.nfe.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.sharing.BatchReceiptSearchResponse;
import eprecise.efiscal4j.nfe.sharing.BatchReceiptSearchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.sharing.EventDispatchResponse;
import eprecise.efiscal4j.nfe.sharing.EventDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.sharing.NFeDispatchResponse;
import eprecise.efiscal4j.nfe.sharing.NFeDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearch;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearchResponse;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearch;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponse;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponseMethod;


@XmlRegistry
public class ObjectFactory {

    public static final String ENVI_NFE = "enviNFe";

    public static final String RET_ENVI_NFE = "retEnviNFe";

    public static final String CONS_RECI_NFE = "consReciNFe";

    public static final String RET_CONS_RECI_NFE = "retConsReciNFe";

    public static final String CONS_SIT_NFE = "consSitNFe";

    public static final String RET_CONS_SIT_NFE = "retConsSitNFe";

    public static final String CONS_STAT_SERV = "consStatServ";

    public static final String RET_CONS_STAT_SERV = "retConsStatServ";

    public static final String ENV_EVENTO = "envEvento";

    public static final String RET_ENV_EVENTO = "retEnvEvento";

    public static final String NFE_STAT_SERV_RESULT = "nfeStatusServicoNFResult";

    public static final String NFE_CONS_NFE_RESULT = "nfeConsultaNFResult";

    public static final String NFE_AUT_LOTE_RESULT = "nfeAutorizacaoLoteResult";

    public static final String NFE_RET_AUT_RESULT = "nfeRetAutorizacaoResult";

    public static final String NFE_REC_EVENTO_RESULT = "nfeRecepcaoEventoResult";

    @XmlElementDecl(name = ObjectFactory.ENVI_NFE)
    public JAXBElement<NFeDispatch> createNFeDispatch(NFeDispatch transmissible) {
        return new JAXBElement<NFeDispatch>(new QName(ObjectFactory.ENVI_NFE), NFeDispatch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_ENVI_NFE)
    public JAXBElement<NFeDispatchResponse> createNFeDispatchResponse(NFeDispatchResponse receivableBody) {
        return new JAXBElement<NFeDispatchResponse>(new QName(ObjectFactory.RET_ENVI_NFE), NFeDispatchResponse.class, receivableBody);
    }

    @XmlElementDecl(name = ObjectFactory.CONS_RECI_NFE)
    public JAXBElement<BatchReceiptSearch> createBatchReceiptSearch(BatchReceiptSearch transmissible) {
        return new JAXBElement<BatchReceiptSearch>(new QName(ObjectFactory.CONS_RECI_NFE), BatchReceiptSearch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_CONS_RECI_NFE)
    public JAXBElement<BatchReceiptSearchResponse> createBatchReceiptSearchResponse(BatchReceiptSearchResponse receivableBody) {
        return new JAXBElement<BatchReceiptSearchResponse>(new QName(ObjectFactory.RET_CONS_RECI_NFE), BatchReceiptSearchResponse.class, receivableBody);
    }

    @XmlElementDecl(name = ObjectFactory.CONS_SIT_NFE)
    public JAXBElement<NFeStatusSearch> createNFeStatusSearch(NFeStatusSearch transmissible) {
        return new JAXBElement<NFeStatusSearch>(new QName(ObjectFactory.CONS_SIT_NFE), NFeStatusSearch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_CONS_SIT_NFE)
    public JAXBElement<NFeStatusSearchResponse> createNFeStatusSearchResponse(NFeStatusSearchResponse transmissible) {
        return new JAXBElement<NFeStatusSearchResponse>(new QName(ObjectFactory.RET_CONS_SIT_NFE), NFeStatusSearchResponse.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.CONS_STAT_SERV)
    public JAXBElement<ServiceStatusSearch> createServiceStatusSearch(ServiceStatusSearch transmissible) {
        return new JAXBElement<ServiceStatusSearch>(new QName(ObjectFactory.CONS_STAT_SERV), ServiceStatusSearch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_CONS_STAT_SERV)
    public JAXBElement<ServiceStatusSearchResponse> createServiceStatusSearchResponse(ServiceStatusSearchResponse receivableBody) {
        return new JAXBElement<ServiceStatusSearchResponse>(new QName(ObjectFactory.RET_CONS_STAT_SERV), ServiceStatusSearchResponse.class, receivableBody);
    }

    @XmlElementDecl(name = ObjectFactory.ENV_EVENTO)
    public JAXBElement<EventDispatch> createEventDispatch(EventDispatch transmissible) {
        return new JAXBElement<EventDispatch>(new QName(ObjectFactory.ENV_EVENTO), EventDispatch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_ENV_EVENTO)
    public JAXBElement<EventDispatchResponse> createEventDispatchResponse(EventDispatchResponse receivableBody) {
        return new JAXBElement<EventDispatchResponse>(new QName(ObjectFactory.RET_ENV_EVENTO), EventDispatchResponse.class, receivableBody);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_STAT_SERV_RESULT)
    public JAXBElement<ServiceStatusSearchResponseMethod> createServiceStatusSearchResponseMethod(ServiceStatusSearchResponseMethod receivable) {
        return new JAXBElement<ServiceStatusSearchResponseMethod>(new QName(ObjectFactory.NFE_STAT_SERV_RESULT), ServiceStatusSearchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_CONS_NFE_RESULT)
    public JAXBElement<NFeStatusSearchResponseMethod> createNFeStatusSearchResponseMethod(NFeStatusSearchResponseMethod receivable) {
        return new JAXBElement<NFeStatusSearchResponseMethod>(new QName(ObjectFactory.NFE_CONS_NFE_RESULT), NFeStatusSearchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_AUT_LOTE_RESULT)
    public JAXBElement<NFeDispatchResponseMethod> createNFeDispatchResponseMethod(NFeDispatchResponseMethod receivable) {
        return new JAXBElement<NFeDispatchResponseMethod>(new QName(ObjectFactory.NFE_AUT_LOTE_RESULT), NFeDispatchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_RET_AUT_RESULT)
    public JAXBElement<BatchReceiptSearchResponseMethod> createBatchReceiptSearchResponseMethod(BatchReceiptSearchResponseMethod receivable) {
        return new JAXBElement<BatchReceiptSearchResponseMethod>(new QName(ObjectFactory.NFE_RET_AUT_RESULT), BatchReceiptSearchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_REC_EVENTO_RESULT)
    public JAXBElement<EventDispatchResponseMethod> createEventDispatchResponseMethod(EventDispatchResponseMethod receivable) {
        return new JAXBElement<EventDispatchResponseMethod>(new QName(ObjectFactory.NFE_REC_EVENTO_RESULT), EventDispatchResponseMethod.class, receivable);
    }
}
