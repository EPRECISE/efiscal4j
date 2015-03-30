/**
 * 
 */

package eprecise.efiscal4j.commons.properties;

import java.util.Properties;


/**
 * Carregador de arquivos de propriedades, classe imutável, otimizada para realizar processamento de variáveis nos valores das propriedades
 * 
 * @author Clécius J. Martinkoski
 * 
 */
public class PropertiesLoader {

    private final Properties properties;

    private PropertiesLoader(Properties properties) {
        this.properties = properties;
    }

    /**
     * Carrega determinada propriedade do arquivo através da chave desta, recebe um valor padrão e caso o a chave solicitada não exista o valor padrão é retornado
     * 
     * @param key
     *            - chave solicitada
     * @param defaultValue
     *            - valor para caso a propriedade não exista
     * @return o valor da chave solicitada ou o valor padrão caso o primeiro não exista
     */
    public <T> T valueFrom(String key, T defaultValue) {
        @SuppressWarnings("unchecked") Class<? extends T> clazz = (Class<? extends T>) defaultValue.getClass();

        T value = this.valueFrom(key, clazz);

        return value == null ? defaultValue : value;
    }

    /**
     * Carrega determinada propriedade do arquivo através da chave desta e retorna no tipo solicitado
     * 
     * @param key
     *            - chave solicitada
     * @param clazz
     *            - classe na qual se espera o retorno
     * @return o valor da chave solicitada no tipo solicitado
     */
    public <T> T valueFrom(String key, Class<? extends T> clazz) {
        try {
            return this.castTo(this.valueFrom(key), clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Carrega determinada propriedade do arquivo através da chave desta e retorna como string
     * 
     * @param key
     *            - chave solicitada
     * @return o valor da chave solicitada como string
     */
    public String valueFrom(String key) {
        return this.properties.containsKey(key) ? this.processValue(String.valueOf(this.properties.getProperty(key))) : null;
    }

    /**
     * Método que interpreta chamadas as variáveis do sistema e substitui o devido valor, caso o valor parametrizado não tenha referencia a variáveis, o valor retornado será o parametrizado
     * 
     * @param value
     *            - string a ser processada
     * @return o valor da string processada
     */
    private String processValue(String value) {
        return new Expander().expand(value, new MultiplePropertySource(new JvmVariablesPropertySource(), new EnvVariablesPropertiesSource()));
    }

    private <T> T castTo(String value, Class<? extends T> clazz) {
        if (value == null) {
            return null;
        } else if (Integer.class.equals(clazz)) {
            return clazz.cast(Integer.valueOf(value));
        } else if (String.class.equals(clazz)) {
            return clazz.cast(value);
        } else {
            return clazz.cast(value);
        }
    }

    /**
     * Objeto responsável pela construção de um PropertiesLoader
     * 
     * @author Clécius J. Martinkoski
     * 
     */
    public static class Builder {

        private String fileName;

        private Class<?> clazz = Builder.class;

        public Builder from(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder resourceLoader(Class<?> clazz) {
            this.clazz = clazz;
            return this;
        }

        public PropertiesLoader create() {
            try {
                Properties properties = new Properties();
                properties.load(this.clazz.getResourceAsStream(this.fileName));
                return new PropertiesLoader(properties);
            } catch (Exception e) {
                throw new InvalidFileException(e);
            }
        }

    }

    /**
     * Exceção emitida caso o arquivo passado para o builder seja inválido
     * 
     * @author Clécius J. Martinkoski
     * 
     */
    public static class InvalidFileException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public InvalidFileException() {
        }

        public InvalidFileException(Exception cause) {
            super(cause);
        }

    }

}
