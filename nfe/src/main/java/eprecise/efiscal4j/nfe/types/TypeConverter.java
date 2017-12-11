
package eprecise.efiscal4j.nfe.types;

public interface TypeConverter<T, R> {

    R parse(T source);

    T serialize(R data);
}
