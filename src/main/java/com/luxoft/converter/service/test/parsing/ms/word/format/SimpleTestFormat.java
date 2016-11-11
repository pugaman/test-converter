package com.luxoft.converter.service.test.parsing.ms.word.format;

import com.luxoft.converter.service.test.parsing.ParsingFormat;

import java.util.regex.Pattern;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class SimpleTestFormat implements ParsingFormat {

	private static final Pattern QUESTION_START_PATTERN = Pattern.compile("^[0-9]+\\)");

	@Override
	public boolean isQuestion(String text) {
		return QUESTION_START_PATTERN.matcher(text).matches();
	}

	@Override
	public boolean isAnswer(String text) {
		return false;
	}

	@Override
	public boolean isCorrectAnswer(String text) {
		return false;
	}
}
