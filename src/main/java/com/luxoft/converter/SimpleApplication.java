package com.luxoft.converter;

import com.luxoft.converter.ui.SwingUIRunner;
import com.luxoft.converter.ui.UIRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;

@SpringBootApplication
@ComponentScan(basePackages = {"com.luxoft.converter.configuration"})
public class SimpleApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SimpleApplication.class);
		application.setHeadless(false);

		//Create and show GUI
		ApplicationContext applicationContext = application.run(args);
		SwingUtilities.invokeLater(applicationContext.getBean(UIRunner.class));
	}

	@Bean
	public UIRunner getUIRunner(){
		return new SwingUIRunner();
	}
}
