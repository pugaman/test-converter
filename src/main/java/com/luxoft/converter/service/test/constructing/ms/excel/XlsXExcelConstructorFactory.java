package com.luxoft.converter.service.test.constructing.ms.excel;

import com.luxoft.converter.service.test.constructing.ConstructingFormat;
import com.luxoft.converter.service.test.constructing.DocumentConstructor;
import com.luxoft.converter.service.test.constructing.DocumentConstructorFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class XlsXExcelConstructorFactory implements DocumentConstructorFactory {

	@Override
	public DocumentConstructor createConstructor(File template, File targetDirectory, String resultsFileName) {
		try {
			return new XlsXExcelConstructor(template, targetDirectory, resultsFileName);
		} catch (IOException | InvalidFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
}
