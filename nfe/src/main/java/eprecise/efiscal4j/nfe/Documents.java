
package eprecise.efiscal4j.nfe;



public interface Documents {

    /**
     * CPN - Cadastro nacional de pessoa sendo que para:
     * 
     * - Pessoas Jurídicas: CNPJ
     * 
     * - Pessoas Físicas: CPF
     * 
     * @return CPN correspondente e formatado de acordo com os respecitvos padrões
     */
    String getMaskedCnp();

    /**
     * CPN - Cadastro nacional de pessoa sendo que para:
     * 
     * - Pessoas Jurídicas: CNPJ
     * 
     * - Pessoas Físicas: CPF
     * 
     * @return CPN correspondente e formatado de acordo com os respectivos padrões
     */
    String getCnp();

    String getStateRegistration();

    String getMunicipalRegistration();

    void setStateRegistration(String stateRegistration);

    void setMunicipalRegistration(String municipalRegistration);

}
