package com.softicar.platform.emf.editor;

import com.softicar.platform.common.core.interfaces.INullaryVoidFunction;
import com.softicar.platform.emf.attribute.IEmfAttribute;
import com.softicar.platform.emf.attribute.input.IEmfInput;
import com.softicar.platform.emf.table.row.IEmfTableRow;
import com.softicar.platform.emf.validation.result.IEmfDiagnostic;
import com.softicar.platform.emf.validation.result.IEmfValidationResult;

public class EmfAttributeValueInputFrame<R extends IEmfTableRow<R, ?>, V> extends AbstractEmfAttributeValueFrame<R, V> {

	private final R row;
	private final IEmfInput<V> input;
	private boolean mandatory;

	public EmfAttributeValueInputFrame(IEmfAttribute<R, V> attribute, R row, IEmfInput<V> input) {

		super(attribute, input);

		this.row = row;
		this.input = input;
		this.mandatory = false;
	}

	@Override
	public boolean isEditable() {

		return true;
	}

	@Override
	public boolean isMandatory() {

		return mandatory;
	}

	public IEmfInput<V> getInput() {

		return input;
	}

	public void setChangeCallback(INullaryVoidFunction callback) {

		input.setChangeCallback(callback);
	}

	public void setMandatory(boolean mandatory) {

		this.mandatory = mandatory;
		input.setMandatory(mandatory);
	}

	public void refreshInputConstraints() {

		input.refreshInputConstraints();
	}

	public void executePostSaveHook() {

		input.executePostSaveHook();
	}

	public void applyFromTableRow() {

		input.setValue(attribute.getValue(row));
	}

	public void applyToTableRow() {

		attribute.setValue(row, input.getValueOrThrow());
	}

	public void showDiagnostics(IEmfValidationResult validationResult) {

		clear();

		for (IEmfDiagnostic diagnostic: validationResult.getDiagnostics(attribute)) {
			setErrorState();
			addMessage(diagnostic.getMessage());
		}
	}
}