
package eprecise.efiscal4j.nfe.v400.types;

public interface TypeConverter<T, R> {

    R parse(T source);

    T serialize(R data);
}
