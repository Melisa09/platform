package com.softicar.platform.dom.elements.dialog;

import com.softicar.platform.common.core.i18n.IDisplayString;
import com.softicar.platform.dom.DomI18n;
import com.softicar.platform.dom.elements.DomElementsImages;
import com.softicar.platform.dom.elements.button.DomButton;
import com.softicar.platform.dom.event.IDomEnterKeyEventHandler;
import com.softicar.platform.dom.event.IDomEvent;
import com.softicar.platform.dom.input.DomTextInput;
import com.softicar.platform.dom.input.IDomFocusable;
import com.softicar.platform.dom.input.IDomTextualInput;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * A custom modal dialog that replaces a native "prompt" dialog.
 *
 * @author Alexander Schmidt
 */
public class DomModalPromptPopup extends DomModalDialogPopup {

	private final InputElement inputElement;
	private final Consumer<String> promptHandler;

	/**
	 * Constructs a new {@link DomModalConfirmPopup} that displays the given
	 * message, an input element, and "OK" / "Cancel" buttons.
	 *
	 * @param promptHandler
	 *            the handler to be processed in case the user clicks "OK"
	 *            (never <i>null</i>)
	 * @param message
	 *            the message to display (never <i>null</i>)
	 * @param defaultValue
	 *            the initial value of the input element (may be <i>null</i>)
	 */
	public DomModalPromptPopup(Consumer<String> promptHandler, IDisplayString message, String defaultValue) {

		this.promptHandler = Objects.requireNonNull(promptHandler);
		this.inputElement = new InputElement(defaultValue);

		appendContent(message);
		appendContent(inputElement);

		appendActionNode(new OkayButton());
		appendCancelButton().addMarker(DomModalPromptMarker.CANCEL_BUTTON);
	}

	@Override
	public void open() {

		super.open();
		IDomFocusable.focusFirst(IDomTextualInput.class, this);
	}

	private void closeAndApplyInput() {

		close();
		promptHandler.accept(inputElement.getInputText());
	}

	private class InputElement extends DomTextInput implements IDomEnterKeyEventHandler {

		public InputElement(String defaultValue) {

			setInputText(defaultValue);
			select();
			addMarker(DomModalPromptMarker.INPUT_ELEMENT);
		}

		@Override
		public void handleEnterKey(IDomEvent event) {

			closeAndApplyInput();
		}
	}

	private class OkayButton extends DomButton {

		public OkayButton() {

			setLabel(DomI18n.OK);
			setIcon(DomElementsImages.DIALOG_OKAY.getResource());
			setClickCallback(() -> closeAndApplyInput());
			addMarker(DomModalPromptMarker.OKAY_BUTTON);
		}
	}
}
