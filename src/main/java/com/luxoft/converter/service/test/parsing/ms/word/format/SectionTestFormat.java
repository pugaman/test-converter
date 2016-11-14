package com.luxoft.converter.service.test.parsing.ms.word.format;

import com.luxoft.converter.service.test.parsing.TestParsingFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class SectionTestFormat implements TestParsingFormat {

	private static final Pattern QUESTION_START_PATTERN = Pattern.compile("^[0-9]+\\.[\\s\\xA0]");
	private static final Pattern ANSWER_START_PATTERN = Pattern.compile("^[a-z]\\)[\\s\\xA0]");
	private static final Pattern CORRECT_ANSWER_MARK_PATTERN = Pattern.compile("^!");

	@Override
	public String getFormatName() {
		return "Section format";
	}

	@Override
	public boolean isQuestion(String text) {
		Matcher matcher = QUESTION_START_PATTERN.matcher(text.trim());
		return matcher.find() && matcher.start() == 0;
	}

	@Override
	public String getQuestionText(String text) {
		return QUESTION_START_PATTERN.matcher(text.trim()).replaceFirst("");
	}

	@Override
	public boolean isAnswer(String text) {
		Matcher matcher = ANSWER_START_PATTERN.matcher(text.trim());
		return matcher.find() && matcher.start() == 0;
	}

	@Override
	public String getAnswerText(String text) {
		String textWithoutPrefix = ANSWER_START_PATTERN.matcher(text.trim()).replaceFirst("");
		return CORRECT_ANSWER_MARK_PATTERN.matcher(textWithoutPrefix).replaceFirst("");
	}

	@Override
	public boolean isCorrectAnswer(String text) {
		String textWithoutPrefix = ANSWER_START_PATTERN.matcher(text.trim()).replaceFirst("");
		Matcher matcher = CORRECT_ANSWER_MARK_PATTERN.matcher(textWithoutPrefix);
		return matcher.find() && matcher.start() == 0;
	}
}
