package com.luxoft.converter.ui.component.bar.menu;

import com.luxoft.converter.ui.component.action.menu.ExitAction;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class MainMenuBar extends JMenuBar {

	public MainMenuBar() {
		super();
	}

	@PostConstruct
	protected void init(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new ExitAction());

		add(fileMenu);
	}
}
