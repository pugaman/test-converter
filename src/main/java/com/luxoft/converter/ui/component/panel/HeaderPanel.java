package com.luxoft.converter.ui.component.panel;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class HeaderPanel extends JPanel {

	public HeaderPanel() {
		super();
	}

	@PostConstruct
	protected void init(){
		JTextArea textArea = new JTextArea(2, 30);
		textArea.setText("To convert a test from Word format to Excel format please choose files and press Start button");
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFocusable(false);
		textArea.setBackground(UIManager.getColor("Label.background"));
		textArea.setFont(UIManager.getFont("Label.font"));
		textArea.setBorder(UIManager.getBorder("Label.border"));
		add(textArea);
	}
}
