package com.luxoft.converter.configuration;

import com.luxoft.converter.service.file.VirtualFileStorage;
import com.luxoft.converter.ui.component.bar.menu.MainMenuBar;
import com.luxoft.converter.ui.component.custom.CustomFileChooser;
import com.luxoft.converter.ui.component.custom.CustomFormatChooser;
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
	public JMenuBar mainMenuBar(){
		return new MainMenuBar();
	}

	@Bean(name = "mainPanel")
	public JPanel mainPanel(){
		return new MainPanel();
	}

	@Bean(name = "headerPanel")
	public JPanel headerPanel(){
		return new HeaderPanel();
	}

	@Bean(name = "centralPanel")
	public JPanel centralPanel(){
		return new CentralPanel();
	}

	@Bean(name = "footerPanel")
	public JPanel footerPanel(){
		return new FooterPanel();
	}

	@Bean(name = "testFileChooser")
	public CustomFileChooser testFileChooser(){
		return new CustomFileChooser("Choose test file", VirtualFileStorage.TEST_FILE);
	}

	@Bean(name = "questionsFileChooser")
	public CustomFileChooser questionsFileChooser(){
		return new CustomFileChooser("Choose questions template file", VirtualFileStorage.QUESTIONS_FILE);
	}

	@Bean(name = "answersFileChooser")
	public CustomFileChooser answersFileChooser(){
		return new CustomFileChooser("Choose answers template file", VirtualFileStorage.ANSWERS_FILE);
	}

	@Bean(name = "resultsFileChooser")
	public CustomFileChooser resultsFileChooser(){
		return new CustomFileChooser("Choose target folder for results", VirtualFileStorage.RESULTS_FILE);
	}

	@Bean(name = "testFormatChooser")
	public CustomFormatChooser testFormatChooser(){
		return new CustomFormatChooser();
	}


}
