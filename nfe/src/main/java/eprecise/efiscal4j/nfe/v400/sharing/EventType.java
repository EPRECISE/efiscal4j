
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;
import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;


/**
 * Tipo de Evento
 * 
 * @author Felipe Bueno
 *
 */
@XmlType
@XmlEnum(String.class)
public enum EventType implements Serializable {
                                               @XmlEnumValue("210200")
                                               CONFIRMACAO_OPERACAO("210200", "", "Confirmação da Operação"),
                                               @XmlEnumValue("210210")
                                               CIENCIA_OPERACAO("210210", "", "Ciência da Operação"),
                                               @XmlEnumValue("210220")
                                               DESCONHECIMENTO_OPERACAO("210220", "", "Desconhecimento da Operação"),
                                               @XmlEnumValue("210240")
                                               OPERACAO_NAO_REALIZADA("210240", "", "Operação não Realizada"),
                                               @XmlEnumValue("110110")
                                               CCE("110110", "Carta de Correcao", "Carta de Correção Eletrônica"),
                                               @XmlEnumValue("110111")
                                               CANC_NFE("110111", "Cancelamento", "Cancelamento de NF-e"),
                                               @XmlEnumValue("110112﻿﻿")
                                               ENCERRAMENTO("110112﻿﻿", "", "Encerramento"),
                                               @XmlEnumValue("110113")
                                               EPEC("110113", "", "EPEC"),
                                               @XmlEnumValue("110114")
                                               INCLUSAO_CONDUTOR("110114", "", "Inclusão Condutor"),
                                               @XmlEnumValue("110115")
                                               NAO_EMBARQUE("110115", "", "Não Embarque"),
                                               @XmlEnumValue("110140")
                                               EPEC_NFE("110140", "", "EPEC NF-e"),
                                               @XmlEnumValue("110160")
                                               MULTIMODAL("110160", "", "MultiModal"),
                                               @XmlEnumValue("610130")
                                               COMPROVANTE_ENTREGA_CTE("610130", "", "Comprovante de Entrega do CT-e"),
                                               @XmlEnumValue("610500")
                                               PASSAGEM_AUTORIZACAO("610500", "", "Passagem Autorização"),
                                               @XmlEnumValue("610501")
                                               CANCELAMENTO_PASSAGEM("610501", "", "Cancelamento de Passagem"),
                                               @XmlEnumValue("610510")
                                               PASSAGEM_NFE_MDFE("610510", "", "Passagem de NF-e MDF-e"),
                                               @XmlEnumValue("610514")
                                               PASSAGEM_NFE_CTE("610514", "", "Passagem de NF-e CT-e"),
                                               @XmlEnumValue("610550")
                                               PASSAGEM_NFE_RFID("610550", "", "Passagem NF-e RFID"),
                                               @XmlEnumValue("610552")
                                               PASSAGEM_AUTOMATICA_MDFE("610552", "", "Passagem Automática MDF-e"),
                                               @XmlEnumValue("610554")
                                               PASSAGEM_AUTOMATICO_MDFE_CTE("610554", "", "Passagem Automatico MDF-e com CT-e"),
                                               @XmlEnumValue("610600")
                                               AUTORIZACAO_CTE_NFE("610600", "", "Autorização de CT-e para a NF-e"),
                                               @XmlEnumValue("610601")
                                               CANCELAMENTO_CTE_NFE("610601", "", "Cancelamento de CT-e para a NF-e"),
                                               @XmlEnumValue("610610")
                                               MDFE_AUTORIZADO("610610", "", "MDF-e Autorizado"),
                                               @XmlEnumValue("610611")
                                               MDFE_CANCELADO("610611", "", "MDF-e Cancelado"),
                                               @XmlEnumValue("610614")
                                               MDFE_AUTORIZADO_CTE("610614", "", "MDF-e Autorizado com CT-e"),
                                               @XmlEnumValue("610615")
                                               CANCELAMENT0_MDFE_AUTORIZADO_CTE("610615", "", "Cancelamento de MDF-e Autorizado com CT-e"),
                                               @XmlEnumValue("310610")
                                               MDFE_AUTORIZADO2("310610", "", "MDF-e Autorizado 2"),
                                               @XmlEnumValue("310611")
                                               MDFE_CANCELADO2("310611", "", "MDF-e Cancelado 2"),
                                               @XmlEnumValue("310620")
                                               REGISTRO_PASSAGEM("310620", "", "Registro de Passagem"),
                                               @XmlEnumValue("510620")
                                               PASSAGEM_AUTOMATICA("510620", "", "Passagem Automática"),
                                               @XmlEnumValue("790700")
                                               AVERBACAO_EXPORTACAO("790700", "", "Averbação Exportação"),
                                               @XmlEnumValue("990910")
                                               INTERNALIZACAO_SUFRAMA("990910", "", "Internalização SUFRAMA"),
                                               @XmlEnumValue("990900")
                                               VISTORIA_SUFRAMA("990900", "", "Vistoria SUFRAMA"),
                                               @XmlEnumValue("630690")
                                               VISTORIA_SUFRAMA_SEFAZ("630690", "", "Vistoria SUFRAMA SEFAZ"),
                                               @XmlEnumValue("111500")
                                               PEDIDO_PRORROGACAO_PRAZO_1("111500", "", "Pedido de Prorrogação de Prazo 1"),
                                               @XmlEnumValue("111501")
                                               PEDIDO_PRORROGACAO_PRAZO_2("111501", "", "Pedido de Prorrogação de Prazo 2"),
                                               @XmlEnumValue("111502")
                                               CANCELAMENTO_PEDIDO_PRORROGACAO_PRAZO_1("111502", "", "Cancelamento de Pedido de Prorrogação de Prazo 1"),
                                               @XmlEnumValue("111503")
                                               CANCELAMENTO_PEDIDO_PRORROGACAO_PRAZO_2("111503", "", "Cancelamento de Pedido de Prorrogação de Prazo 2"),
                                               @XmlEnumValue("411500")
                                               FISCO_RESPOSTA_PEDIDO_PRORROGACAO_1("411500", "", "Fisco em Resposta ao Pedido de Prorrogação 1"),
                                               @XmlEnumValue("411501")
                                               FISCO_RESPOSTA_PEDIDO_PRORROGACAO_2("411501", "", "Fisco em Resposta ao Pedido de Prorrogação 2"),
                                               @XmlEnumValue("411502")
                                               FISCO_RESPOSTA_CANCELAMENTO_PEDIDO_PRORROGACAO_1("411502", "", "Fisco em Resposta de Cancelamento ao Pedido de Prorrogação 1"),
                                               @XmlEnumValue("411503")
                                               FISCO_RESPOSTA_CANCELAMENTO_PEDIDO_PRORROGACAO_2("411503", "", "Fisco em Resposta de Cancelamento ao Pedido de Prorrogação 2"),
                                               @XmlEnumValue("000000﻿")
                                               CTE_AUTORIZADO("000000﻿", "", "CT-e Autorizado"),
                                               @XmlEnumValue("610110")
                                               DESACORDO("610110", "", "Desacordo"),
                                               @XmlEnumValue("110170")
                                               GTV("110170", "", "GTV"),
                                               @XmlEnumValue("240130")
                                               MDFE_COMPLEMENTAR_AUTORIZADO("240130", "", "MDF-e Complementar Autorizado"),
                                               @XmlEnumValue("240131")
                                               CANCELA_CTE_COMPLEMENTAR("240131", "", "Cancela CT-e Complementar"),
                                               @XmlEnumValue("240140")
                                               CTE_SUBSTITUICAO("240140", "", "CT-e Substituição"),
                                               @XmlEnumValue("240150")
                                               CTE_ANULACAO("240150", "", "CT-e Anulação"),
                                               @XmlEnumValue("240160")
                                               LIBERACAO_EPEC("240160", "", "Liberação EPEC"),
                                               @XmlEnumValue("240170")
                                               LIBERA_PRAZO_CANCELAMENTO("240170", "", "Libera Prazo Cancelamento"),
                                               @XmlEnumValue("440130")
                                               AUTORIZADO_REDESPACHO("440130", "", "Autorizado Redespacho"),
                                               @XmlEnumValue("440140")
                                               AUTORIZA_REDESPACHO_INTERMEDIARIO("440140", "", "Autoriza Redespacho Intermediario"),
                                               @XmlEnumValue("440150")
                                               AUTORIZADO_SUBCONTRATACAO("440150", "", "Autorizado Subcontratação"),
                                               @XmlEnumValue("440160")
                                               AUTORIZA_SERVICO_MULTIMODAL("440160", "", "Autoriza Serviço Multimodal"),
                                               @XmlEnumValue("110116")
                                               ALTERACAO_POLTRONA("110116", "", "Alteração Poltrona");

    private static final long serialVersionUID = 1L;

    private String code;

    private String description;

    private String fullDescription;

    private EventType(String code, String description, String fullDescription) {
        this.code = code;
        this.description = description;
        this.fullDescription = fullDescription;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFullDescription() {
        return this.fullDescription;
    }

    public String getFullDescriptionWithNoAccents() {
        return StringUtils.stripAccents(this.fullDescription);
    }

    public static EventType findByCode(String code) {
        for (final EventType eventType : EventType.values()) {
            if (eventType.getCode().equals(code)) {
                return eventType;
            }
        }
        return null;
    }

    public static EventType findByFullDescriptionWithNoAccents(String eventDescription) {
        if (StringUtils.isEmpty(eventDescription)) {
            return null;
        }

        return Stream.of(EventType.values()).filter(et -> et.getFullDescriptionWithNoAccents().equals(StringUtils.stripAccents(eventDescription))).findFirst().orElse(null);
    }
}
