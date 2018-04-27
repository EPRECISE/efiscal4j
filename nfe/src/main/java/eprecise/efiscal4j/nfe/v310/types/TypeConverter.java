
package eprecise.efiscal4j.nfe.v310.types;

public interface TypeConverter<T, R> {

    R parse(T source);

    T serialize(R data);
}
