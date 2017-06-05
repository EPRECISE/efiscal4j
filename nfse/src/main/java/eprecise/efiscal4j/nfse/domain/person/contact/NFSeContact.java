
package eprecise.efiscal4j.nfse.domain.person.contact;

import java.io.Serializable;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeContact implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String phone;

    private final String email;

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

        public NFSeContact build() {
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
