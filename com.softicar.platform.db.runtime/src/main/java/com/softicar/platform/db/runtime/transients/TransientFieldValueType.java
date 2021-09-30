package com.softicar.platform.db.runtime.transients;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class TransientFieldValueType<V> implements ITransientFieldValueType<V> {

	private final Class<V> valueClass;
	private final Optional<Comparator<V>> comparator;

	public TransientFieldValueType(Class<V> valueClass) {

		this.valueClass = Objects.requireNonNull(valueClass);
		this.comparator = Optional.empty();
	}

	public TransientFieldValueType(Class<V> valueClass, Comparator<V> comparator) {

		this.valueClass = Objects.requireNonNull(valueClass);
		this.comparator = Optional.of(comparator);
	}

	@Override
	public Class<V> getValueClass() {

		return valueClass;
	}

	@Override
	public Optional<Comparator<V>> getComparator() {

		return comparator;
	}
}
