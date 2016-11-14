package com.luxoft.converter.service.test.parsing.ms.word.format;

import com.luxoft.converter.service.test.parsing.TestParsingFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pavel on 10.11.2016.
 */
public class BABOKTestFormat implements TestParsingFormat {

	private static final Pattern QUESTION_START_PATTERN = Pattern.compile("^[0-9]{1,2}\\-[0-9]{3}[\\s\\xA0]");
	private static final Pattern ANSWER_START_PATTERN = Pattern.compile("^[a-z]\\.[\\s\\xA0]");
	private static final Pattern CORRECT_ANSWER_MARK_PATTERN = Pattern.compile("^Correct\\.[\\s\\xA0]");
	private static final Pattern INCORRECT_ANSWER_MARK_PATTERN = Pattern.compile("^Incorrect\\.[\\s\\xA0]");

	@Override
	public String getFormatName() {
		return "BABOK format";
	}

	@Override
	public boolean isQuestion(String text) {
		Matcher matcher = QUESTION_START_PATTERN.matcher(text);
		return matcher.find() && matcher.start() == 0;
	}

	@Override
	public String getQuestionText(String text) {
		return QUESTION_START_PATTERN.matcher(text).replaceFirst("");
	}

	@Override
	public boolean isAnswer(String text) {
		Matcher matcher = ANSWER_START_PATTERN.matcher(text);
		return matcher.find() && matcher.start() == 0;
	}

	@Override
	public String getAnswerText(String text) {
		String textWithoutPrefix = ANSWER_START_PATTERN.matcher(text).replaceFirst("");
		if(isCorrectAnswer(text)){
			return CORRECT_ANSWER_MARK_PATTERN.matcher(textWithoutPrefix).replaceFirst("");
		} else {
			return INCORRECT_ANSWER_MARK_PATTERN.matcher(textWithoutPrefix).replaceFirst("");
		}
	}

	@Override
	public boolean isCorrectAnswer(String text) {
		String textWithoutPrefix = ANSWER_START_PATTERN.matcher(text).replaceFirst("");
		Matcher matcher = CORRECT_ANSWER_MARK_PATTERN.matcher(textWithoutPrefix);
		return matcher.find() && matcher.start() == 0;
	}
}
