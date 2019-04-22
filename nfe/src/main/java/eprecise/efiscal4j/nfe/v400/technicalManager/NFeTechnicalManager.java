
package eprecise.efiscal4j.nfe.v400.technicalManager;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeCNPJOptional;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Informações do Responsável Técnico pela emissão do DF-e
 *
 * @author Fernando C. Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeTechnicalManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJOptional String cnpj;

    private final @XmlElement(name = "xContato") @NotNull @Size(min = 2, max = 60) @NFeString String contactName;

    private final @XmlElement(name = "email") @NotNull @Size(min = 6, max = 60) @NFeString String email;

    private final @XmlElement(name = "fone") @NotNull String phone;

    private final @XmlElement(name = "idCSRT") String csrtId;

    private final @XmlElement(name = "hashCSRT") String csrtHash;

    public static class Builder {

        private String cnpj;

        private String contactName;

        private String email;

        private String phone;

        private String csrtId;

        private String csrtHash;

        /**
         * CNPJ da empresa responsável técnico
         * 
         * @param cnpj
         * @return
         */
        public Builder withCnpj(final String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        /**
         * Nome da pessoa a ser contatada na empresa desenvolvedora do sistema utilizado na emissão do documento fiscal eletrônico
         * 
         * @param contactName
         * @return
         */
        public Builder withContactName(final String contactName) {
            this.contactName = contactName;
            return this;
        }

        /**
         * E-mail da pessoa a ser contatada na empresa desenvolvedora do sistema
         * 
         * @param email
         * @return
         */
        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        /**
         * Telefone da pessoa a ser contatada na empresa desenvolvedora do sistema. Preencher com o Código DDD + número do telefone.
         * 
         * @param phone
         * @return
         */
        public Builder withPhone(final String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * Identificador do CSRT utilizado para montar o hash do CSRT
         * 
         * @param csrtId
         * @return
         */
        public Builder withCsrtId(final String csrtId) {
            this.csrtId = csrtId;
            return this;
        }

        /**
         * O hashCSRT é o resultado da função hash (SHA-1 – Base64) do CSRT fornecido pelo fisco mais a Chave de Acesso da NFe
         * 
         * @param csrtHash
         * @return
         */
        public Builder withCsrtHash(final String csrtHash) {
            this.csrtHash = csrtHash;
            return this;
        }

        public NFeTechnicalManager build() {
            final NFeTechnicalManager entity = new NFeTechnicalManager(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeTechnicalManager() {
        this.cnpj = null;
        this.contactName = null;
        this.email = null;
        this.phone = null;
        this.csrtId = null;
        this.csrtHash = null;
    }

    public NFeTechnicalManager(final Builder builder) {
        this.cnpj = builder.cnpj;
        this.contactName = builder.contactName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.csrtId = builder.csrtId;
        this.csrtHash = builder.csrtHash;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCsrtId() {
        return csrtId;
    }

    public String getCsrtHash() {
        return csrtHash;
    }

}
