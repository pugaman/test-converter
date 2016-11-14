package com.luxoft.converter.service.format;

import com.luxoft.converter.service.test.parsing.TestParsingFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.BABOKTestFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.SectionTestFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.SimpleTestFormat;

import java.util.*;

/**
 * Created by Pavel on 14.11.2016.
 */
public class TestParsingFormatHolder {

    private static final List<TestParsingFormat> TEST_FILE_FORMATS;

    static {
        TEST_FILE_FORMATS = new ArrayList<>();
        TEST_FILE_FORMATS.add(new SimpleTestFormat());
        TEST_FILE_FORMATS.add(new SectionTestFormat());
        TEST_FILE_FORMATS.add(new BABOKTestFormat());
    }

    private TestParsingFormat testParsingFormat;

    public TestParsingFormat getTestParsingFormat() {
        return testParsingFormat;
    }

    public void setTestParsingFormat(TestParsingFormat testParsingFormat) {
        this.testParsingFormat = testParsingFormat;
    }

    public static List<TestParsingFormat> getTestFileFormats(){
        return Collections.unmodifiableList(TEST_FILE_FORMATS);
    }
}
