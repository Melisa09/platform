package com.softicar.platform.ajax.testing.cases;

import com.softicar.platform.ajax.image.AjaxImages;
import com.softicar.platform.common.core.i18n.IDisplayString;
import com.softicar.platform.dom.elements.button.DomButton;

public class ClickDisplacementTestCase2 extends AbstractTestCaseDiv {

	public ClickDisplacementTestCase2() {

		appendChild(new PromptButton());
	}

	private class PromptButton extends DomButton {

		public PromptButton() {

			setIcon(AjaxImages.EMBLEM_AUTO_COMPLETE_VALUE_VALID.getResource());
			setLabel(IDisplayString.create("Click to get a prompt."));
			setClickCallback(this::handleClick);
		}

		private void handleClick() {

			executePrompt(//
				text -> log("got input: '%s'", text),
				IDisplayString.create("Please enter something and press ENTER."),
				"");
		}
	}
}