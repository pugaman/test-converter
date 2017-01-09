package com.luxoft.converter.service.test.parsing.ms.word.format;

import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.service.code.QuestionCodeProvider;
import com.luxoft.converter.service.test.parsing.TestParsingFormat;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.List;

/**
 * Created by pgolovenkov on 15.11.2016.
 */
public interface DocXTestParsingFormat extends TestParsingFormat {

	List<Question> analyzeParagraph(XWPFParagraph paragraph, Question lastQuestion, QuestionCodeProvider questionCodeProvider);

}
