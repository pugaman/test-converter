package com.luxoft.converter.service.test.constructing.ms.excel;

import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.service.test.constructing.DocumentConstructor;
import com.luxoft.converter.service.test.constructing.listener.DocumentConstructorListener;
import com.luxoft.converter.service.test.constructing.ConstructingFormat;

import java.io.File;
import java.util.List;

/**
 * Created by Pavel on 10.11.2016.
 */
public class XlsXExcelConstructor implements DocumentConstructor{

	private final ConstructingFormat format;

	public XlsXExcelConstructor(ConstructingFormat format) {
		this.format = format;
	}

	@Override
	public void construct(List<Question> questions, File targetFile){

	}

	@Override
	public void addListener(DocumentConstructorListener listener) {

	}
}
