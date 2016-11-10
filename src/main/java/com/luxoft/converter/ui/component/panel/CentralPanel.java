package com.luxoft.converter.ui.component.panel;

import com.luxoft.converter.ui.component.custom.CustomFileChooser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class CentralPanel extends JPanel {

	@Autowired
	CustomFileChooser testFileChooser;

	@Autowired
	CustomFileChooser questionsFileChooser;

	@Autowired
	CustomFileChooser answersFileChooser;

	public CentralPanel() {
		super();
	}

	@PostConstruct
	protected void init(){
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 5, 0, 5);
		constraints.gridx = 0;

		constraints.gridy = 0;
		add(testFileChooser, constraints);

		constraints.gridy = 1;
		add(questionsFileChooser, constraints);

		constraints.gridy = 2;
		add(answersFileChooser, constraints);
	}
}
