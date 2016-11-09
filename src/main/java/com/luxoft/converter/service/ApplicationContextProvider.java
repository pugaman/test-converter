package com.luxoft.converter.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class ApplicationContextProvider implements ApplicationContextAware{

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextProvider.applicationContext = applicationContext;
	}


}
