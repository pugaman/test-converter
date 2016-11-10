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
		setLayout(new BorderLayout());

		//Add panes
		add(headerPanel, BorderLayout.PAGE_START);
		add(centralPanel, BorderLayout.LINE_START);
		add(footerPanel, BorderLayout.PAGE_END);
	}
}
