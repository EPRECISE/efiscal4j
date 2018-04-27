
package eprecise.efiscal4j.nfe.v400.deliveryDFe;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum NFeDeliveryDFeResponseStatus implements Serializable {
                                                                  @XmlEnumValue("128")
                                                                  LOT_PROCESSED("128", "Lote de Evento Processado", true),
                                                                  @XmlEnumValue("135")
                                                                  EVENT_IS_REGISTERED_AND_ATTACHED_TO_NFE("135", "Evento registrado e vinculado a NF-e", true),
                                                                  @XmlEnumValue("136")
                                                                  EVENT_IS_REGISTERED_BUT_NOT_ATTACHED_TO_NFE("136", "Evento registrado, mas não vinculado a NF-e", true),
                                                                  @XmlEnumValue("137")
                                                                  NO_DOCUMENT_FOUND_FOR_RECIPIENT("137", "Nenhum documento localizado para o Destinatário", true),
                                                                  @XmlEnumValue("138")
                                                                  DOCUMENT_FOUND_FOR_RECIPIENT("138", "Documento localizado para o Destinatário", true),
                                                                  @XmlEnumValue("139")
                                                                  DOWNLOAD_REQUEST_IS_PROCESSED("139", "Pedido de Download processado", true),
                                                                  @XmlEnumValue("140")
                                                                  DOWNLOAD_IS_AVAILABLE("140", "Download disponibilizado", true),
                                                                  @XmlEnumValue("489")
                                                                  INVALID_CNPJ("489", "Rejeição: CNPJ informado inválido (DV ou zeros)", false),
                                                                  @XmlEnumValue("490")
                                                                  INVALID_CPF("490", "Rejeição: CPF informado inválido (DV ou zeros)", false),
                                                                  @XmlEnumValue("491")
                                                                  INVALID_TP_EVENT("491", "Rejeição: O tpEvento informado inválido", false),
                                                                  @XmlEnumValue("492")
                                                                  INVALID_VER_EVENT("492", "Rejeição: O verEvento informado inválido", false),
                                                                  @XmlEnumValue("493")
                                                                  EVENT_DOESNOT_MATCH_SCHEMA("493", "Rejeição: Evento não atende o Schema XML específico", false),
                                                                  @XmlEnumValue("494")
                                                                  ACCESS_KEY_DOESNOT_EXIST("494", "Rejeição: Chave de Acesso inexistente", false),
                                                                  @XmlEnumValue("572")
                                                                  INVALID_ID("572",
                                                                          "Rejeição: Erro Atributo ID do evento não corresponde a concatenação dos campos (\"ID\" + tpEvento + chNFe + nSeqEvento)",
                                                                          false),
                                                                  @XmlEnumValue("573")
                                                                  EVENT_IS_DUPLICATED("573", "Rejeição: Duplicidade de Evento", false),
                                                                  @XmlEnumValue("574")
                                                                  DIVERGENT_EMITTER_FROM_EVENT_AUTHOR("574", "Rejeição: O autor do evento diverge do emissor da NF-e", false),
                                                                  @XmlEnumValue("575")
                                                                  DIVERGENT_RECEIVER_FROM_EVENT_AUTHOR("575", "Rejeição: O autor do evento diverge do destinatário da NF-e", false),
                                                                  @XmlEnumValue("576")
                                                                  EVENT_AUTHOR_IS_NOT_AUTHORIZED("576", "Rejeição: O autor do evento não é um órgão autorizado a gerar o evento", false),
                                                                  @XmlEnumValue("577")
                                                                  EVENT_DATE_IS_BEFORE_NFE_EMISSION_DATE("577", "Rejeição: A data do evento não pode ser menor que a data de emissão da NF-e", false),
                                                                  @XmlEnumValue("578")
                                                                  EVENT_DATE_IS_BEFORE_PROCESSING_DATE("578", "Rejeição: A data do evento não pode ser maior que a data do processamento", false),
                                                                  @XmlEnumValue("579")
                                                                  EVENT_DATE_IS_BEFORE_VALID_NFE_AUTHORIZATION_DATE("579",
                                                                          "Rejeição: A data do evento não pode ser menor que a data de autorização para NF-e não emitida em contingência", false),
                                                                  @XmlEnumValue("580")
                                                                  EVENT_REQUIRES_AUTHORIZED_NFE("580", "Rejeição: O evento exige uma NF-e autorizada", false),
                                                                  @XmlEnumValue("587")
                                                                  INVALID_NFE_NAMESPACE("587", "Rejeição: Usar somente o namespace padrão da NF-e", false),
                                                                  @XmlEnumValue("588")
                                                                  MESSAGE_TAGS_WITH_EDITION_CHARACTERS("588",
                                                                          "Rejeição: Não é permitida a presença de caracteres de edição no início/fim da mensagem ou entre as tags da mensagem", false),
                                                                  @XmlEnumValue("589")
                                                                  NSU_GREATER_THAN_THE_DATABASE("589", "Rejeição: Número do NSU informado superior ao maior NSU da base de dados da SEFAZ", false),
                                                                  @XmlEnumValue("593")
                                                                  CNPJ_DIVERGES_FROM_DIGITAL_CERTIFICATE("593", "Rejeição: CNPJ-Base consultado difere do CNPJ-Base do Certificado Digital", false),
                                                                  @XmlEnumValue("594")
                                                                  EVENT_SEQ_NUMBER_IS_GREATER_THAN_ALLOWED("594", "Rejeição: O número de sequencia do evento informado é maior que o permitido", false),
                                                                  @XmlEnumValue("595")
                                                                  EVENT_JUSTIFICATION_IS_REQUIRED("595", "Rejeição: Obrigatória a informação da justificativa do evento", false),
                                                                  @XmlEnumValue("596")
                                                                  EVENT_IS_EXPIRED("596", "Rejeição: Evento apresentado fora do prazo: [prazo vigente]", false),
                                                                  @XmlEnumValue("614")
                                                                  INVALID_UF_ON_ACCESS_KEY("614", "Rejeição: Chave de Acesso inválida (Código UF inválido)", false),
                                                                  @XmlEnumValue("615")
                                                                  INVALID_YEAR_ON_ACCESS_KEY("615", "Rejeição: Chave de Acesso inválida (Ano menor que 06 ou Ano maior que Ano corrente)", false),
                                                                  @XmlEnumValue("616")
                                                                  INVALID_MONTH_ON_ACCESS_KEY("616", "Rejeição: Chave de Acesso inválida (Mês menor que 1 ou Mês maior que 12)", false),
                                                                  @XmlEnumValue("617")
                                                                  INVALID_CNPJ_ON_ACCESS_KEY("617", "Rejeição: Chave de Acesso inválida (CNPJ zerado ou dígito inválido)", false),
                                                                  @XmlEnumValue("618")
                                                                  INVALID_MODEL_OF_ACCESS_KEY("618", "Rejeição: Chave de Acesso inválida (modelo diferente de 55)", false),
                                                                  @XmlEnumValue("619")
                                                                  INVALID_NF_NUMBER_ON_ACCESS_KEY("619", "Rejeição: Chave de Acesso inválida (número NF = 0)", false),
                                                                  @XmlEnumValue("631")
                                                                  RECIPIENT_CNPJ_DIVERGES_FROM_DIGITAL_CERTIFICATE("631",
                                                                          "Rejeição: CNPJ-Base do Destinatário difere do CNPJ-Base do Certificado Digital", false),
                                                                  @XmlEnumValue("632")
                                                                  DOWNLOAD_IS_NOT_AVAILABLE_DUE_TO_REQUEST_EXPIRATION("632",
                                                                          "Rejeição: Solicitação fora de prazo, a NF-e não está mais disponível para download", false),
                                                                  @XmlEnumValue("633")
                                                                  DOWNLOAD_IS_NOT_AVAILABLE_DUE_TO_ABSENCE_OF_RECIPIENT_MANIFESTATION("633",
                                                                          "Rejeição: NF-e indisponível para download devido a ausência de Manifestação do Destinatário", false),
                                                                  @XmlEnumValue("634")
                                                                  RECIPIENT_CNPJ_DIVERGES_FROM_DOWNLOAD_REQUESTER("634",
                                                                          "Rejeição: Destinatário da NF-e não tem o mesmo CNPJ raiz do solicitante do download", false),
                                                                  @XmlEnumValue("650")
                                                                  AWARENESS_EVENT_IS_CANCELLED_OR_DENIED("650", "Rejeição: Evento de \"Ciência da Operação\" para NF-e Cancelada ou Denegada", false),
                                                                  @XmlEnumValue("651")
                                                                  DENIAL_EVENT_IS_CANCELLED_OR_DENIED("651", "Rejeição: Evento de \"Desconhecimento da Operação\" para NF-e Cancelada ou Denegada",
                                                                          false),
                                                                  @XmlEnumValue("653")
                                                                  DOWNLOAD_IS_NOT_AVAILABLE_DUE_TO_NFE_CANCELLATION("653", "Rejeição: NF-e Cancelada, arquivo indisponível para download", false),
                                                                  @XmlEnumValue("654")
                                                                  DOWNLOAD_IS_NOT_AVAILABLE_DUE_TO_NFE_DENIAL("654", "Rejeição: NF-e Denegada, arquivo indisponível para download", false),
                                                                  @XmlEnumValue("655")
                                                                  AWARENESS_EVENT_POSTED_AFTER_FINAL_MANIFESTATION("655",
                                                                          "Rejeição: Evento de Ciência da Operação informado após a manifestação final do destinatário", false),
                                                                  @XmlEnumValue("656")
                                                                  UNDUE_CONSUPTION("656", "Rejeição: Consumo Indevido", false),
                                                                  @XmlEnumValue("657")
                                                                  AGENCY_CODE_DIVERGES_FROM_AUTHORIZER_AGENCY("657", "Rejeição: Código do Órgão diverge do órgão autorizador", false),
                                                                  @XmlEnumValue("658")
                                                                  ACCESS_KEY_RECIPIENT_UF_DIGERGES_FROM_AUTHORIZER_UF("658",
                                                                          "Rejeição: UF do destinatário da Chave de Acesso diverge da UF autorizadora", false);

    private final String code;

    private final String description;

    private final boolean processed;

    NFeDeliveryDFeResponseStatus(String code, String description, boolean processed) {
        this.code = code;
        this.description = description;
        this.processed = processed;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isProcessed() {
        return this.processed;
    }

    public Optional<NFeDeliveryDFeResponseStatus> getByCode(String code) {
        return Stream.of(NFeDeliveryDFeResponseStatus.values()).filter(s -> s.getCode().equals(code)).findFirst();
    }

    @Override
    public String toString() {
        return this.description;
    }

}
