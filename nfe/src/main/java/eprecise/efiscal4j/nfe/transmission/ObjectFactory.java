
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
import eprecise.efiscal4j.nfe.sharing.NFeNumberDisableDispatch;
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

    public static final String NFE_STAT_INUT_RESULT = "nfeInutilizacaoNFResult";

    public static final String INUT_NFE = "inutNFe";

    public static final String RET_INUT_NFE = "retInutNFe";

    @XmlElementDecl(name = ObjectFactory.ENVI_NFE)
    public JAXBElement<NFeDispatch> createNFeDispatch(final NFeDispatch transmissible) {
        return new JAXBElement<>(new QName(ObjectFactory.ENVI_NFE), NFeDispatch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_ENVI_NFE)
    public JAXBElement<NFeDispatchResponse> createNFeDispatchResponse(final NFeDispatchResponse receivableBody) {
        return new JAXBElement<>(new QName(ObjectFactory.RET_ENVI_NFE), NFeDispatchResponse.class, receivableBody);
    }

    @XmlElementDecl(name = ObjectFactory.CONS_RECI_NFE)
    public JAXBElement<BatchReceiptSearch> createBatchReceiptSearch(final BatchReceiptSearch transmissible) {
        return new JAXBElement<>(new QName(ObjectFactory.CONS_RECI_NFE), BatchReceiptSearch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_CONS_RECI_NFE)
    public JAXBElement<BatchReceiptSearchResponse> createBatchReceiptSearchResponse(final BatchReceiptSearchResponse receivableBody) {
        return new JAXBElement<>(new QName(ObjectFactory.RET_CONS_RECI_NFE), BatchReceiptSearchResponse.class, receivableBody);
    }

    @XmlElementDecl(name = ObjectFactory.CONS_SIT_NFE)
    public JAXBElement<NFeStatusSearch> createNFeStatusSearch(final NFeStatusSearch transmissible) {
        return new JAXBElement<>(new QName(ObjectFactory.CONS_SIT_NFE), NFeStatusSearch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_CONS_SIT_NFE)
    public JAXBElement<NFeStatusSearchResponse> createNFeStatusSearchResponse(final NFeStatusSearchResponse transmissible) {
        return new JAXBElement<>(new QName(ObjectFactory.RET_CONS_SIT_NFE), NFeStatusSearchResponse.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.CONS_STAT_SERV)
    public JAXBElement<ServiceStatusSearch> createServiceStatusSearch(final ServiceStatusSearch transmissible) {
        return new JAXBElement<>(new QName(ObjectFactory.CONS_STAT_SERV), ServiceStatusSearch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_CONS_STAT_SERV)
    public JAXBElement<ServiceStatusSearchResponse> createServiceStatusSearchResponse(final ServiceStatusSearchResponse receivableBody) {
        return new JAXBElement<>(new QName(ObjectFactory.RET_CONS_STAT_SERV), ServiceStatusSearchResponse.class, receivableBody);
    }

    @XmlElementDecl(name = ObjectFactory.ENV_EVENTO)
    public JAXBElement<EventDispatch> createEventDispatch(final EventDispatch transmissible) {
        return new JAXBElement<>(new QName(ObjectFactory.ENV_EVENTO), EventDispatch.class, transmissible);
    }

    @XmlElementDecl(name = ObjectFactory.RET_ENV_EVENTO)
    public JAXBElement<EventDispatchResponse> createEventDispatchResponse(final EventDispatchResponse receivableBody) {
        return new JAXBElement<>(new QName(ObjectFactory.RET_ENV_EVENTO), EventDispatchResponse.class, receivableBody);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_STAT_SERV_RESULT)
    public JAXBElement<ServiceStatusSearchResponseMethod> createServiceStatusSearchResponseMethod(final ServiceStatusSearchResponseMethod receivable) {
        return new JAXBElement<>(new QName(ObjectFactory.NFE_STAT_SERV_RESULT), ServiceStatusSearchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_CONS_NFE_RESULT)
    public JAXBElement<NFeStatusSearchResponseMethod> createNFeStatusSearchResponseMethod(final NFeStatusSearchResponseMethod receivable) {
        return new JAXBElement<>(new QName(ObjectFactory.NFE_CONS_NFE_RESULT), NFeStatusSearchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_AUT_LOTE_RESULT)
    public JAXBElement<NFeDispatchResponseMethod> createNFeDispatchResponseMethod(final NFeDispatchResponseMethod receivable) {
        return new JAXBElement<>(new QName(ObjectFactory.NFE_AUT_LOTE_RESULT), NFeDispatchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_RET_AUT_RESULT)
    public JAXBElement<BatchReceiptSearchResponseMethod> createBatchReceiptSearchResponseMethod(final BatchReceiptSearchResponseMethod receivable) {
        return new JAXBElement<>(new QName(ObjectFactory.NFE_RET_AUT_RESULT), BatchReceiptSearchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.NFE_REC_EVENTO_RESULT)
    public JAXBElement<EventDispatchResponseMethod> createEventDispatchResponseMethod(final EventDispatchResponseMethod receivable) {
        return new JAXBElement<>(new QName(ObjectFactory.NFE_REC_EVENTO_RESULT), EventDispatchResponseMethod.class, receivable);
    }

    @XmlElementDecl(name = ObjectFactory.INUT_NFE)
    public JAXBElement<NFeNumberDisableDispatch> createNFeNumberDisable(final NFeNumberDisableDispatch transmissible) {
        return new JAXBElement<>(new QName(ObjectFactory.INUT_NFE), NFeNumberDisableDispatch.class, transmissible);
    }
}
