
package eprecise.efiscal4j.commons.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Collections2;


public class ValidationBuilder<T> {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final Logger logger = LoggerFactory.getLogger(ValidationBuilder.class);

    private final Collection<T> objects;

    private final Collection<Class<?>> groups = new LinkedHashSet<>();

    public ValidationBuilder(final Collection<T> objects) {
        this.objects = objects;
        this.groups.add(Default.class);
    }

    public static <T> ValidationBuilder<T> from(final Collection<T> objects) {
        return new ValidationBuilder<>(objects);
    }

    @SafeVarargs
    public static <T> ValidationBuilder<T> fromAll(final T... objects) {
        return new ValidationBuilder<>(Arrays.asList(objects));
    }

    public static <T> ValidationBuilder<T> from(final T object) {
        return new ValidationBuilder<>(Arrays.asList(object));
    }

    public ValidationBuilder<T> withGroups(final Class<?>... groups) {
        this.groups.addAll(Arrays.asList(groups));
        return this;
    }

    public ValidationBuilder<T> withoutDefaultGroup() {
        this.groups.remove(Default.class);
        return this;
    }

    public ValidationResult<T> validate() {
        return new ValidationResultConcrete();
    }

    public interface ValidationResult<T> {

        public void throwIfViolate();

        public Collection<ConstraintViolation<T>> getViolations();

        public Collection<T> getValids();

        public Collection<T> getInvalids();
    }

    class ValidationResultConcrete implements ValidationResult<T> {

        @Override
        public void throwIfViolate() {
            final Validator validator = factory.getValidator();
            final Set<ConstraintViolation<?>> violations = new LinkedHashSet<>();

            for (final T object : ValidationBuilder.this.objects) {
                violations.addAll(validator.validate(object, ValidationBuilder.this.getGroupsArray()));
            }

            if (!violations.isEmpty()) {
                violations.forEach(v -> ValidationBuilder.this.logger.error(String.format("%s value '%s' %s", v.getPropertyPath(), v.getInvalidValue(), v.getMessage())));
                throw new ConstraintViolationException(violations);
            }

        }

        @Override
        public Collection<ConstraintViolation<T>> getViolations() {
            final Validator validator = factory.getValidator();
            final Collection<ConstraintViolation<T>> violations = new LinkedHashSet<>();
            for (final T object : ValidationBuilder.this.objects) {
                violations.addAll(validator.validate(object, ValidationBuilder.this.getGroupsArray()));
            }
            return violations;
        }

        @Override
        public Collection<T> getValids() {
            final Validator validator = factory.getValidator();
            return new LinkedHashSet<>(Collections2.filter(ValidationBuilder.this.objects, obj -> validator.validate(obj, ValidationBuilder.this.getGroupsArray()).size() == 0));
        }

        @Override
        public Collection<T> getInvalids() {
            final Validator validator = factory.getValidator();
            return new LinkedHashSet<>(Collections2.filter(ValidationBuilder.this.objects, obj -> validator.validate(obj, ValidationBuilder.this.getGroupsArray()).size() > 0));
        }

    }

    public Class<?>[] getGroupsArray() {
        return this.groups.toArray(new Class<?>[this.groups.size()]);
    }

}
