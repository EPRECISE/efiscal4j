
package eprecise.efiscal4j.nfse.domain.person;

import java.io.Serializable;

import eprecise.efiscal4j.nfse.domain.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.domain.person.contact.NFSeContact;
import eprecise.efiscal4j.nfse.domain.person.documents.NFSeDocuments;


@SuppressWarnings("unchecked")
public abstract class NFSePerson implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    private final NFSeDocuments documents;

    private final NFSeAddress address;

    private final NFSeContact contact;

    public abstract static class Builder<T> {

        private String name;

        private NFSeDocuments documents;

        private NFSeAddress address;

        private NFSeContact contact;

        public T withName(final String name) {
            this.name = name;
            return (T) this;
        }

        public T withDocuments(final NFSeDocuments documents) {
            this.documents = documents;
            return (T) this;
        }

        public T withAddress(final NFSeAddress address) {
            this.address = address;
            return (T) this;
        }

        public T withContact(final NFSeContact contact) {
            this.contact = contact;
            return (T) this;
        }
    }

    public NFSePerson() {
        name = null;
        documents = null;
        address = null;
        contact = null;
    }

    public NFSePerson(final Builder<?> builder) {
        name = builder.name;
        documents = builder.documents;
        address = builder.address;
        contact = builder.contact;
    }

    public NFSeDocuments getDocuments() {
        return documents;
    }

    public NFSeAddress getAddress() {
        return address;
    }

    public NFSeContact getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

}
