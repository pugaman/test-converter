package com.luxoft.converter.ui.component.panel;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class MainPanel extends JPanel {

	private static final int ROW_NUMBER = 3;
	private static final int COL_NUMBER = 1;

	@Autowired
	private JPanel headerPanel;
	@Autowired
	private JPanel centralPanel;
	@Autowired
	private JPanel footerPanel;

	public MainPanel() {
		super();
	}

	@PostConstruct
	protected void init() {
		//Set grid layout with suitable constraints
		setLayout(new GridLayout(ROW_NUMBER, COL_NUMBER));

		//Add panes
		add(headerPanel);
		add(centralPanel);
		add(footerPanel);
	}
}
