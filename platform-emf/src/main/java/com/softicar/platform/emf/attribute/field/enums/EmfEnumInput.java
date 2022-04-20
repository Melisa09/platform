package com.softicar.platform.emf.attribute.field.enums;

import com.softicar.platform.common.core.interfaces.INullaryVoidFunction;
import com.softicar.platform.dom.element.IDomElement;
import com.softicar.platform.dom.elements.DomEnumSelect;
import com.softicar.platform.dom.event.IDomChangeEventHandler;
import com.softicar.platform.dom.event.IDomEvent;
import com.softicar.platform.emf.EmfI18n;
import com.softicar.platform.emf.attribute.input.AbstractEmfInputDiv;
import java.util.Optional;

public class EmfEnumInput<E extends Enum<E>> extends AbstractEmfInputDiv<E> {

	private final ChangeListeningEnumSelect<E> enumInput;

	public EmfEnumInput(Class<E> enumClass) {

		this(enumClass.getEnumConstants());
	}

	@SafeVarargs
	public EmfEnumInput(E...enums) {

		this.enumInput = new ChangeListeningEnumSelect<>();
		this.enumInput.addNilValue(EmfI18n.NONE.encloseInBrackets());
		this.enumInput.addValues(enums);
		appendChild(enumInput);
	}

	@Override
	public Optional<E> getValue() {

		return Optional.ofNullable(enumInput.getSelectedValue());
	}

	@Override
	public void setValueAndHandleChangeCallback(E value) {

		setValue(value);
		enumInput.applyChangeCallback();
	}

	@Override
	public void setValue(E value) {

		enumInput.setSelectedValue(value);
	}

	@Override
	public void setChangeCallback(INullaryVoidFunction callback) {

		enumInput.setChangeCallback(callback);
	}

	@Override
	public IDomElement setEnabled(boolean enabled) {

		enumInput.setEnabled(enabled);
		return this;
	}

	private static class ChangeListeningEnumSelect<E extends Enum<E>> extends DomEnumSelect<E> implements IDomChangeEventHandler {

		private INullaryVoidFunction callback;

		public void setChangeCallback(INullaryVoidFunction callback) {

			this.callback = callback;
		}

		public void applyChangeCallback() {

			Optional//
				.ofNullable(callback)
				.ifPresent(INullaryVoidFunction::apply);
		}

		@Override
		public void handleChange(IDomEvent event) {

			if (callback != null) {
				callback.apply();
			}
		}
	}
}
