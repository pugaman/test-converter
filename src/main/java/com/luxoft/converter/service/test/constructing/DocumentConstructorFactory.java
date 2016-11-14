package com.luxoft.converter.service.test.constructing;

import java.io.File;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public interface DocumentConstructorFactory {

	DocumentConstructor createConstructor(File file);
}
