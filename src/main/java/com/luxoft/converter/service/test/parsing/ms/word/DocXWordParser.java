package com.luxoft.converter.service.test.parsing.ms.word;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.model.domain.ResponseType;
import com.luxoft.converter.service.test.parsing.DocumentParser;
import com.luxoft.converter.service.test.parsing.TestParsingFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.DocXTestParsingFormat;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class DocXWordParser implements DocumentParser {

    private static final Random RANDOM = new Random();
    private static final Integer LOWER_BOUND = 200000;
    private static final String NEW_LINE_SIGN = "\n";

    private final DocXTestParsingFormat format;

    DocXWordParser(DocXTestParsingFormat format) {
        this.format = format;
    }

    public List<Question> parse(File file) {

        final List<Question> questions = new ArrayList<>();
        int questionReferenceNumber = LOWER_BOUND;

        try (FileInputStream inputStream = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(inputStream)) {

            //Mock question for start counting reference number
            Question lastQuestion = new Question("", LOWER_BOUND - 1);

            final List<IBodyElement> elements = document.getBodyElements();
            for (final IBodyElement element : elements) {
                //We are not interesting in Tables and Contents
                if (!(element instanceof XWPFParagraph)) {
                    continue;
                }

                final XWPFParagraph paragraph = (XWPFParagraph) element;
                List<Question> questionsInner = format.analyzeParagraph(paragraph, lastQuestion);
                if(questionsInner.size() > 0) {
                    questions.addAll(questionsInner);
                    lastQuestion = questionsInner.get(questionsInner.size() - 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }


}
