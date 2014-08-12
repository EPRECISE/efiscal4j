
package eprecise.efiscal4j.nfe.utils;

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

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;


public class ValidationBuilder<T> {

	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private final Collection<T> objects;

	private final Collection<Class<?>> groups = new LinkedHashSet<>();

	public ValidationBuilder(Collection<T> objects) {
		this.objects = objects;
		this.groups.add(Default.class);
	}

	public static <T> ValidationBuilder<T> from(Collection<T> objects) {
		return new ValidationBuilder<>(objects);
	}

	@SafeVarargs
	public static <T> ValidationBuilder<T> fromAll(T... objects) {
		return new ValidationBuilder<>(Arrays.asList(objects));
	}

	public static <T> ValidationBuilder<T> from(T object) {
		return new ValidationBuilder<>(Arrays.asList(object));
	}

	public ValidationBuilder<T> withGroups(Class<?>... groups) {
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
			Set<ConstraintViolation<?>> violations = new LinkedHashSet<>();

			for (T object : objects) {
				violations.addAll(validator.validate(object, getGroupsArray()));
			}

			if (!violations.isEmpty()) throw new ConstraintViolationException(violations);

		}

		@Override
		public Collection<ConstraintViolation<T>> getViolations() {
			final Validator validator = factory.getValidator();
			Collection<ConstraintViolation<T>> violations = new LinkedHashSet<>();
			for (T object : objects) {
				violations.addAll(validator.validate(object, getGroupsArray()));
			}
			return violations;
		}

		@Override
		public Collection<T> getValids() {
			final Validator validator = factory.getValidator();
			return new LinkedHashSet<T>(Collections2.filter(objects, new Predicate<T>() {

				@Override
				public boolean apply(T obj) {
					return validator.validate(obj, getGroupsArray()).size() == 0;
				}
			}));
		}

		@Override
		public Collection<T> getInvalids() {
			final Validator validator = factory.getValidator();
			return new LinkedHashSet<T>(Collections2.filter(objects, new Predicate<T>() {

				@Override
				public boolean apply(T obj) {
					return validator.validate(obj, getGroupsArray()).size() > 0;
				}
			}));
		}

	}

	public Class<?>[] getGroupsArray() {
		return groups.toArray(new Class<?>[groups.size()]);
	}

}
