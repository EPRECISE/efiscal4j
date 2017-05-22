
package eprecise.efiscal4j.nfse.tc.person.contacts;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFSeContact {

    private final @XmlElement(name = "Telefone") @Size(min = 1, max = 11) String phone;

    private final @XmlElement(name = "Email") @Size(min = 1, max = 80) String email;

    public static class Builder {

        private String phone;

        private String email;

        /**
         * @param phone
         * @return
         */
        public Builder withPhone(final String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * @param email
         * @return
         */
        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public NFSeContact build() throws Exception {
            final NFSeContact entity = new NFSeContact(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeContact() {
        phone = null;
        email = null;
    }

    public NFSeContact(final Builder builder) {
        phone = builder.phone;
        email = builder.email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}
