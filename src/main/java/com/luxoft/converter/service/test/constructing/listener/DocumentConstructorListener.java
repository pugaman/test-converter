package com.luxoft.converter.service.test.constructing.listener;

import java.util.EventListener;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public interface DocumentConstructorListener extends EventListener{

	void invoke(DocumentConstructorEvent event);

}
