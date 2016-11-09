package com.luxoft.converter.configuration;

import com.luxoft.converter.service.ApplicationContextProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
@Configuration
public class ServiceConfiguration {

	@Bean
	public ApplicationContextProvider getApplicationContextProvider(){
		return new ApplicationContextProvider();
	}
}
