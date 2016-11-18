package com.luxoft.converter.ui.component.panel;

import com.luxoft.converter.ui.component.custom.CustomFileChooser;
import com.luxoft.converter.ui.component.custom.CustomFormatChooser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class CentralPanel extends JPanel {

	@Autowired
	CustomFormatChooser testFormatChooser;

	@Autowired
	List<CustomFileChooser> fileChooserList;

	public CentralPanel() {
		super();
	}

	@PostConstruct
	protected void init() {
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 5, 0, 5);
		constraints.gridx = 0;

		constraints.gridy = 0;
		add(testFormatChooser, constraints);

		for (int i = 1; i <= fileChooserList.size(); i++){
			constraints.gridy = i;
			add(fileChooserList.get(i-1), constraints);
		}
	}
}
