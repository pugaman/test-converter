package com.luxoft.converter.ui.component.action.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class ExitAction extends AbstractAction {

	public ExitAction() {
		super("Exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();

		if (window != null) {
			WindowEvent windowClosing = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
			window.dispatchEvent(windowClosing);
		}
	}
}
