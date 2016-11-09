package com.luxoft.converter.ui.component.panel;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class FooterPanel extends JPanel {

	public FooterPanel() {
		super();
	}

	@PostConstruct
	protected void init(){
		JLabel label = new JLabel("Start");
		add(label);
	}
}
