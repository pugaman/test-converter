package com.luxoft.converter.service.test.parsing;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public interface TestParsingFormat {

	String getFormatName();

	boolean isQuestion(String text);

	String getQuestionText(String text);

	boolean isAnswer(String text);

	String getAnswerText(String text);

	boolean isCorrectAnswer(String text);

}
