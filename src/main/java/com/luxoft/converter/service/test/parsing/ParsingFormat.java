package com.luxoft.converter.service.test.parsing;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public interface ParsingFormat {

	boolean isQuestion(String text);

	boolean isAnswer(String text);

	boolean isCorrectAnswer(String text);



}
