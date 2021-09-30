package com.softicar.platform.emf.data.table.filter.value.input;

import com.softicar.platform.dom.elements.input.DomLongInput;
import com.softicar.platform.emf.data.table.filter.value.IEmfDataTableValueFilterInput;

public class EmfDataTableLongFilterInput extends DomLongInput implements IEmfDataTableValueFilterInput<Long> {

	@Override
	public Long getFilterValue() {

		return getLongOrNull();
	}

	@Override
	public void setFilterValue(Long value) {

		setLong(value);
	}

	@Override
	public void selectFirstInputElement() {

		select();
	}
}
