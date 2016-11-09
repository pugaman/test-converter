package com.luxoft.converter.ui;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class SwingUIRunner implements UIRunner {

	@Autowired
	private JMenuBar mainMenuBar;
	@Autowired
	private JPanel mainPanel;

	@Override
	public void run() {
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Test converter");
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		mainFrame.setJMenuBar(mainMenuBar);
		mainFrame.getContentPane().add(mainPanel);

		mainFrame.pack();
		centerPosition(mainFrame);
		mainFrame.setVisible(true);
	}

	private void centerPosition(JFrame frame){
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = environment.getCenterPoint();

		//Frame aligning depends on frame dimensions
		int newX = (int)centerPoint.getX() - frame.getWidth()/2;
		int newY = (int)centerPoint.getY() - frame.getHeight()/2;
		Point alignedCenterPoint = new Point(newX, newY);

		frame.setLocation(alignedCenterPoint);
	}
}
