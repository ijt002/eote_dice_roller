package gui;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

	@Override
	public void replaceText(int start, int end, String text) {
		if (validate(text)) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (validate(text)) {
			super.replaceSelection(text);
		}
	}

	private boolean validate(String text) {
		if (text.matches("[0-9]") || text == "") {
			return true;
		}
		return false;
	}

	public static Integer getInputNumber(final NumberTextField inputField) {
		String text = inputField.getText();
		if (!text.matches("\\d+")) {
			text = "0";
		}
		return Integer.parseInt(text);
	}

	public Integer getTextValue() {
		String text = getText();
		if (!text.matches("\\d+")) {
			text = "0";
		}
		return Integer.parseInt(text);
	}

	public void setText(Integer input) {
		setText(input.toString());
	}
}