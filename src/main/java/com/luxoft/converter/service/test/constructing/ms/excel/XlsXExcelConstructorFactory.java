package com.luxoft.converter.service.test.constructing.ms.excel;

import com.luxoft.converter.service.test.constructing.ConstructingFormat;
import com.luxoft.converter.service.test.constructing.DocumentConstructor;
import com.luxoft.converter.service.test.constructing.DocumentConstructorFactory;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class XlsXExcelConstructorFactory implements DocumentConstructorFactory {

	@Override
	public DocumentConstructor createConstructor(ConstructingFormat format) {
		return new XlsXExcelConstructor(format);
	}
}
