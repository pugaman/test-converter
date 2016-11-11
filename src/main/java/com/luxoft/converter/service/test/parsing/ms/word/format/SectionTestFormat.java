package com.luxoft.converter.service.test.parsing.ms.word.format;

import com.luxoft.converter.service.test.parsing.ParsingFormat;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class SectionTestFormat implements ParsingFormat{
	@Override
	public boolean isQuestion(String text) {
		return false;
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
