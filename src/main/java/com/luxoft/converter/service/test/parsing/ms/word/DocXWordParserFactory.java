package com.luxoft.converter.service.test.parsing.ms.word;

import com.luxoft.converter.service.test.parsing.DocumentParser;
import com.luxoft.converter.service.test.parsing.DocumentParserFactory;
import com.luxoft.converter.service.test.parsing.ParsingFormat;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class DocXWordParserFactory implements DocumentParserFactory {

	@Override
	public DocumentParser createParser(ParsingFormat format) {
		return new DocXWordParser(format);
	}
}
