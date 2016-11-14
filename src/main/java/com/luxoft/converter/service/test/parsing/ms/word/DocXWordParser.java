package com.luxoft.converter.service.test.parsing.ms.word;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.model.domain.ResponseType;
import com.luxoft.converter.service.test.parsing.DocumentParser;
import com.luxoft.converter.service.test.parsing.TestParsingFormat;
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

    private final TestParsingFormat format;

    DocXWordParser(TestParsingFormat format) {
        this.format = format;
    }

    public List<Question> parse(File file) {

        final List<Question> questions = new ArrayList<>();
        int questionReferenceNumber = LOWER_BOUND;

        try (FileInputStream inputStream = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(inputStream)) {

            Question lastQuestion = null;
            int correctAnswersCount = 0;

            Answer lastAnswer = null;

            final List<IBodyElement> elements = document.getBodyElements();
            for (final IBodyElement element : elements) {
                //We are not interesting in Tables and Contents
                if (!(element instanceof XWPFParagraph)) {
                    continue;
                }

                final XWPFParagraph paragraph = (XWPFParagraph) element;
                final List<XWPFRun> runs = paragraph.getRuns();
                //We are not interesting in space lines
                if (runs.size() == 0) {
                    continue;
                }

                StringBuilder textBuilder = new StringBuilder();

                for (XWPFRun run : runs) {
                    final String runText = run.getText(0);

                    if (format.isQuestion(runText)) {
                        textBuilder = new StringBuilder(runText);
                        lastAnswer = null;
                        lastQuestion = new Question(format.getQuestionText(textBuilder.toString()), questionReferenceNumber++);
                        correctAnswersCount = 0;
                        questions.add(lastQuestion);
                    } else if (format.isAnswer(runText)) {
                        textBuilder = new StringBuilder(runText);
                        final boolean isCorrect = format.isCorrectAnswer(runText);
                        if (lastQuestion != null) {
                            lastAnswer = lastQuestion.addAnswer(format.getAnswerText(textBuilder.toString()), isCorrect);
                            if (isCorrect) {
                                correctAnswersCount++;
                            }
                            if (correctAnswersCount > 1) {
                                lastQuestion.setResponseType(ResponseType.MULTIPLE);
                            }
                        }
                    } else if(lastAnswer != null){
                        textBuilder.append(runText);
                        lastAnswer.setText(format.getAnswerText(textBuilder.toString()));
                    } else if(lastQuestion != null){
                        textBuilder.append(runText);
                        lastQuestion.setText(format.getQuestionText(textBuilder.toString()));
                    }
                }
                lastQuestion = null;
                lastAnswer = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }


}
