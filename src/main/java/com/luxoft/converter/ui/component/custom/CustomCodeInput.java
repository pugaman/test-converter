package com.luxoft.converter.ui.component.custom;

import com.luxoft.converter.service.code.QuestionCodeProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

/**
 * Created by pgolovenkov on 09.01.2017.
 */
public class CustomCodeInput extends JPanel{

	private static final String CODE_PREFIX_FIELD_EMPTY_TEXT = "Enter code prefix";
	private static final int CODE_PREFIX_FIELD_COLUMNS = 20;

	private static final String SAVE_CODE_BUTTON_TEXT = "Save code";

	private static final String CODE_SAVED_LABEL_TEXT = "Saved!";

	@Autowired
	QuestionCodeProvider questionCodeProvider;

	private final JLabel title;

	private final JTextField codePrefixField;
	private final JButton saveCodeButton;
	private final JLabel codeSavedLabel;

	public CustomCodeInput(String titleText) {
		super(new BorderLayout());

		this.title = new JLabel(titleText);
		this.saveCodeButton = new JButton(new SaveCodeAction());
		installSaveCodeButton();

		this.codePrefixField = new JTextField(CODE_PREFIX_FIELD_EMPTY_TEXT, CODE_PREFIX_FIELD_COLUMNS);
		installCodePrefixField();

		this.codeSavedLabel = new JLabel(CODE_SAVED_LABEL_TEXT);
		this.codeSavedLabel.setVisible(false);

		buildComponent();
	}

	private void installCodePrefixField() {
		this.codePrefixField.setEditable(true);
		this.codePrefixField.setBackground(Color.white);
		this.codePrefixField.getDocument().addDocumentListener(new CodeFieldChangeListener());
	}

	private void installSaveCodeButton() {
		this.saveCodeButton.setText(SAVE_CODE_BUTTON_TEXT);
	}

	private void buildComponent() {
		add(title, BorderLayout.PAGE_START);

		JPanel fieldPanel = new JPanel();
		fieldPanel.add(codePrefixField);
		fieldPanel.add(saveCodeButton);
		fieldPanel.add(codeSavedLabel);

		add(fieldPanel, BorderLayout.LINE_START);
	}

	private class CodeFieldChangeListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			codeSavedLabel.setVisible(false);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			codeSavedLabel.setVisible(false);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			codeSavedLabel.setVisible(false);
		}
	}

	private class SaveCodeAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			final String prefix = codePrefixField.getText();
			final boolean isConfigured = questionCodeProvider.configure(prefix);
			codeSavedLabel.setVisible(isConfigured);
		}
	}
}
