package com.luxoft.converter.ui.component.panel;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class FooterPanel extends JPanel {

	@Autowired
	Action startConvertingAction;

	public FooterPanel() {
		super();
	}

	@PostConstruct
	protected void init(){
		JButton startButton = new JButton(startConvertingAction);
		startButton.setText("Start converting");
		add(startButton);
	}
}
