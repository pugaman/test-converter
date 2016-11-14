package com.luxoft.converter.service.test.parsing;

import com.luxoft.converter.service.test.parsing.TestParsingFormat;

/**
 * Created by Pavel on 14.11.2016.
 */
public abstract class AbstractTestParsingFormat implements TestParsingFormat {

    @Override
    public boolean isQuestion(String text) {
        return false;
    }

    @Override
    public String getQuestionText(String text) {
        return null;
    }

    @Override
    public boolean isAnswer(String text) {
        return false;
    }

    @Override
    public String getAnswerText(String text) {
        return null;
    }

    @Override
    public boolean isCorrectAnswer(String text) {
        return false;
    }
}
