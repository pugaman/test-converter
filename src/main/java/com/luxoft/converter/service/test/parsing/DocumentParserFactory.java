package com.luxoft.converter.service.test.parsing;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public interface DocumentParserFactory {

	DocumentParser createParser(ParsingFormat format);

}
