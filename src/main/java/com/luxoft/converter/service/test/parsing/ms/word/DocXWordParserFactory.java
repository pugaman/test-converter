package com.luxoft.converter.service.test.parsing.ms.word;

import com.luxoft.converter.service.code.QuestionCodeProvider;
import com.luxoft.converter.service.test.parsing.DocumentParser;
import com.luxoft.converter.service.test.parsing.DocumentParserFactory;
import com.luxoft.converter.service.test.parsing.TestParsingFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.DocXTestParsingFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.impl.BABOKTestFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.impl.SectionTestFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.impl.SimpleTestFormat;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class DocXWordParserFactory implements DocumentParserFactory {

    @Autowired
    QuestionCodeProvider questionCodeProvider;

    @Override
    public DocumentParser createParser(TestParsingFormat format) {
        return new DocXWordParser((DocXTestParsingFormat)format, questionCodeProvider);
    }

    @Override
    public boolean isSupport(TestParsingFormat format) {
        return format instanceof SimpleTestFormat || format instanceof SectionTestFormat || format instanceof BABOKTestFormat;
    }
}
