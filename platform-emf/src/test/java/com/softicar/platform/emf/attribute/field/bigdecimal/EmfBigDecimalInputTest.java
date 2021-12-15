package com.softicar.platform.emf.attribute.field.bigdecimal;

import com.softicar.platform.common.core.i18n.IDisplayString;
import com.softicar.platform.common.testing.AbstractTest;
import com.softicar.platform.dom.elements.testing.engine.IDomTestEngine;
import com.softicar.platform.dom.elements.testing.engine.document.DomDocumentTestEngine;
import com.softicar.platform.dom.input.IDomStringInputNode;
import com.softicar.platform.emf.EmfI18n;
import com.softicar.platform.emf.attribute.input.EmfInputException;
import com.softicar.platform.emf.test.IEmfTestEngineMethods;
import java.math.BigDecimal;
import org.junit.Rule;
import org.junit.Test;

public class EmfBigDecimalInputTest extends AbstractTest implements IEmfTestEngineMethods {

	@Rule public final IDomTestEngine engine = new DomDocumentTestEngine();
	private final EmfBigDecimalInput input;

	public EmfBigDecimalInputTest() {

		this.input = new EmfBigDecimalInput();

		setNodeSupplier(() -> input);
	}

	@Override
	public IDomTestEngine getEngine() {

		return engine;
	}

	// ------------------------------ getValueOrThrow ------------------------------ //

	@Test
	public void testGetValueOrThrowWithoutScale() {

		enterInputValue("10").assertGetValueOrThrow("10");
		enterInputValue("12").assertGetValueOrThrow("12");
		enterInputValue("12.6").assertGetValueOrThrow("12.6");
		enterInputValue("12.60").assertGetValueOrThrow("12.60");
	}

	@Test
	public void testGetValueOrThrowWithScale() {

		input.setScale(2);

		// scale increase
		enterInputValue("10").assertGetValueOrThrow("10.00");
		enterInputValue("12").assertGetValueOrThrow("12.00");
		enterInputValue("12.3").assertGetValueOrThrow("12.30");

		// scale retention
		enterInputValue("10.00").assertGetValueOrThrow("10.00");
		enterInputValue("12.00").assertGetValueOrThrow("12.00");
		enterInputValue("12.34").assertGetValueOrThrow("12.34");

		// scale reduction
		enterInputValue("12.000").assertGetValueOrThrow("12.00");
		enterInputValue("12.123").assertException(EmfI18n.ONLY_ARG1_DECIMAL_PLACES_ALLOWED.toDisplay(2));
		enterInputValue("12.1230").assertException(EmfI18n.ONLY_ARG1_DECIMAL_PLACES_ALLOWED.toDisplay(2));
		enterInputValue("12.1200").assertGetValueOrThrow("12.12");
	}

	@Test
	public void testGetValueOrThrowWithIllegalFormat() {

		enterInputValue("x").assertException(EmfI18n.INVALID_DECIMAL_NUMBER);
		enterInputValue("12.3x").assertException(EmfI18n.INVALID_DECIMAL_NUMBER);
	}

	@Test
	public void testGetValueOrThrowWithFormatQuirks() {

		enterInputValue("12,34").assertGetValueOrThrow("12.34");
		enterInputValue(" 12.34 ").assertGetValueOrThrow("12.34");
	}

	// ------------------------------ setValue ------------------------------ //

	@Test
	public void testSetValueWithoutScale() {

		setValue("12").assertInputValue("12");
		setValue("12.6").assertInputValue("12.6");
		setValue("12.60").assertInputValue("12.60");
	}

	@Test
	public void testSetValueWithScale() {

		input.setScale(2);

		// scale increase
		setValue("12").assertInputValue("12.00");
		setValue("12.0").assertInputValue("12.00");
		setValue("12.6").assertInputValue("12.60");

		// scale retention
		setValue("12.00").assertInputValue("12.00");
		setValue("12.60").assertInputValue("12.60");
		setValue("12.67").assertInputValue("12.67");

		// scale reduction
		setValue("12.600").assertInputValue("12.60");
		setValue("12.123").assertInputValue("12.123");
		setValue("12.1230").assertInputValue("12.123");
	}

	// ------------------------------ private ------------------------------ //

	private EmfBigDecimalInputTest enterInputValue(String value) {

		findNode(IDomStringInputNode.class).setInputValue(value);
		return this;
	}

	private EmfBigDecimalInputTest setValue(String value) {

		input.setValue(new BigDecimal(value));
		return this;
	}

	private EmfBigDecimalInputTest assertGetValueOrThrow(String expectedValue) {

		assertEquals(expectedValue, input.getValueOrThrow().toPlainString());
		return this;
	}

	private EmfBigDecimalInputTest assertInputValue(String expectedValue) {

		findNode(IDomStringInputNode.class).assertInputValue(expectedValue);
		return this;
	}

	private EmfBigDecimalInputTest assertException(IDisplayString expectedMessage) {

		try {
			input.getValueOrThrow();
			fail("expected exception");
		} catch (EmfInputException exception) {
			assertEquals(expectedMessage.toString(), exception.getMessage());
		}
		return this;
	}
}