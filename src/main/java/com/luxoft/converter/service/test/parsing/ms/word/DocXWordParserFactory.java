package com.luxoft.converter.service.test.parsing.ms.word;

import com.luxoft.converter.service.test.parsing.DocumentParser;
import com.luxoft.converter.service.test.parsing.DocumentParserFactory;
import com.luxoft.converter.service.test.parsing.TestParsingFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.BABOKTestFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.SectionTestFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.SimpleTestFormat;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class DocXWordParserFactory implements DocumentParserFactory {

    @Override
    public DocumentParser createParser(TestParsingFormat format) {
        return new DocXWordParser(format);
    }

    @Override
    public boolean isSupport(TestParsingFormat format) {
        return format instanceof SimpleTestFormat || format instanceof SectionTestFormat || format instanceof BABOKTestFormat;
    }
}
