package com.luxoft.converter.configuration;

import com.luxoft.converter.ui.component.bar.menu.MainMenuBar;
import com.luxoft.converter.ui.component.panel.CentralPanel;
import com.luxoft.converter.ui.component.panel.FooterPanel;
import com.luxoft.converter.ui.component.panel.HeaderPanel;
import com.luxoft.converter.ui.component.panel.MainPanel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
@Configuration
public class UIComponentConfiguration {

	@Bean(name = "mainMenuBar")
	public JMenuBar getMainMenuBar(){
		return new MainMenuBar();
	}

	@Bean(name = "mainPanel")
	public JPanel getMainPanel(){
		return new MainPanel();
	}

	@Bean(name = "headerPanel")
	public JPanel getHeaderPanel(){
		return new HeaderPanel();
	}

	@Bean(name = "centralPanel")
	public JPanel getCentralPanel(){
		return new CentralPanel();
	}

	@Bean(name = "footerPanel")
	public JPanel getFooterPanel(){
		return new FooterPanel();
	}


}
