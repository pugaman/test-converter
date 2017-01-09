package com.luxoft.converter.service.test.parsing.ms.word;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.model.domain.ResponseType;
import com.luxoft.converter.service.code.QuestionCodeProvider;
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

    private final DocXTestParsingFormat format;
    private final QuestionCodeProvider codeProvider;

    DocXWordParser(DocXTestParsingFormat format, QuestionCodeProvider codeProvider) {
        this.format = format;
        this.codeProvider = codeProvider;
    }

    public List<Question> parse(File file) {

        final List<Question> questions = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(inputStream)) {

            //Mock question for start counting reference number
            Question lastQuestion = new Question("", "");

            final List<IBodyElement> elements = document.getBodyElements();
            for (final IBodyElement element : elements) {
                //We are not interesting in Tables and Contents
                if (!(element instanceof XWPFParagraph)) {
                    continue;
                }

                final XWPFParagraph paragraph = (XWPFParagraph) element;
                List<Question> questionsInner = format.analyzeParagraph(paragraph, lastQuestion, codeProvider);
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
