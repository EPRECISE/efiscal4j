
package eprecise.efiscal4j.nfe.v310.deliveryDFe;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class IllegalClassForNFeDeliveryDfeSchema extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalClassForNFeDeliveryDfeSchema() {
        super(String.format("Tipo inválido de objeto para marshall (Tipos válidos: %s)",
                Stream.of(NFeDeliveryDFeSchemas.values()).map(NFeDeliveryDFeSchemas::getMappedClazz).map(Class::getSimpleName).collect(Collectors.toList())));
    }
}
