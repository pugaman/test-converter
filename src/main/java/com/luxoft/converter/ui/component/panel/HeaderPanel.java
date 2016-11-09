package com.luxoft.converter.ui.component.panel;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class HeaderPanel extends JPanel {

	public HeaderPanel() {
		super();
	}

	@PostConstruct
	protected void init(){
		JLabel label = new JLabel("Header panel!!!");
		add(label);
	}
}
