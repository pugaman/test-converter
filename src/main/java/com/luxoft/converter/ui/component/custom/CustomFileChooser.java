package com.luxoft.converter.ui.component.custom;

import com.luxoft.converter.service.file.VirtualFileStorage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static javax.swing.JFileChooser.DIRECTORIES_ONLY;

/**
 * Created by Pavel on 10.11.2016.
 */
public class CustomFileChooser extends JPanel {

	@Autowired
	private VirtualFileStorage virtualFileStorage;

	private static final String FILE_NAME_FIELD_EMPTY_TEXT = "No file is chosen";
	private static final int FILE_NAME_FIELD_COLUMNS = 30;

	private static final String FILE_CHOOSER_OPEN_BUTTON_TEXT = "Choose file";
	private static final String FILE_CHOOSER_APPROVE_BUTTON_TEXT = "Choose";

	private final JLabel title;

	private final JButton openFileChooserButton;
	private final JTextField chosenFileName;

	public CustomFileChooser(String titleText, String fileKind) {
		super(new BorderLayout());

		this.title = new JLabel(titleText);
		this.openFileChooserButton = new JButton(new ChooseFileAction(fileKind));
		installFileChooserButton();

		this.chosenFileName = new JTextField(FILE_NAME_FIELD_EMPTY_TEXT, FILE_NAME_FIELD_COLUMNS);
		installFileNameField();

		buildComponent();
	}

	private void installFileNameField() {
		this.chosenFileName.setEditable(false);
		this.chosenFileName.setBackground(Color.white);
	}

	private void installFileChooserButton() {
		this.openFileChooserButton.setText(FILE_CHOOSER_OPEN_BUTTON_TEXT);
	}

	private void buildComponent() {
		add(title, BorderLayout.PAGE_START);

		JPanel fieldPanel = new JPanel();
		fieldPanel.add(openFileChooserButton);
		fieldPanel.add(chosenFileName);
		add(fieldPanel, BorderLayout.LINE_START);
	}

	@PostConstruct
	protected void init() {
		((ChooseFileAction) this.openFileChooserButton.getAction()).installFileChooser();
	}

	private class ChooseFileAction extends AbstractAction {

		private static final String FILE_FILTER_NAME = "Suitable files";
		private final String fileKind;
		private final JFileChooser fileChooser;

		ChooseFileAction(String fileKind) {
			this.fileKind = fileKind;
			this.fileChooser = new JFileChooser();
		}

		void installFileChooser() {
			String rightFileExtension = virtualFileStorage.getFileKindExtension(this.fileKind);
			if (rightFileExtension != null) {
				this.fileChooser.setFileFilter(new FileFilter() {

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						}

						return f.getName().endsWith(rightFileExtension);
					}

					@Override
					public String getDescription() {
						return FILE_FILTER_NAME;
					}
				});
			} else {
				this.fileChooser.setFileSelectionMode(DIRECTORIES_ONLY);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int result = fileChooser.showDialog(null, FILE_CHOOSER_APPROVE_BUTTON_TEXT);
			if (JFileChooser.APPROVE_OPTION == result) {
				File chosenFile = fileChooser.getSelectedFile();
				virtualFileStorage.storeFile(fileKind, chosenFile);
				chosenFileName.setText(chosenFile.getName());
			}
		}
	}


}
