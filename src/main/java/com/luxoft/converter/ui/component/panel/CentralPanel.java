package com.luxoft.converter.ui.component.panel;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class CentralPanel extends JPanel {

	public CentralPanel() {
		super();
	}

	@PostConstruct
	protected void init(){
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;

		JLabel label = new JLabel("Header panel!!!");
		constraints.gridy = 0;
		add(label, constraints);

		JLabel label2 = new JLabel("Header panel!!!");
		constraints.gridy = 1;
		add(label2, constraints);

		JLabel label3 = new JLabel("Header panel!!!");
		constraints.gridy = 2;
		add(label3, constraints);
	}
}
