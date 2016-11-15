package com.luxoft.converter.service.test.parsing.ms.word.format.impl;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.model.domain.ResponseType;
import com.luxoft.converter.service.test.parsing.ms.word.format.DocXTestParsingFormat;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class SectionTestFormat implements DocXTestParsingFormat {

	private static final Pattern CORRECT_ANSWER_MARK_PATTERN = Pattern.compile("^!");

	private static final String QUESTION_NUM_FORMAT = "decimal";
	private static final String ANSWER_NUM_FORMAT = "bullet";


	@Override
	public String getFormatName() {
		return "Section format";
	}

	@Override
	public List<Question> analyzeParagraph(XWPFParagraph paragraph, Question lastQuestion) {
		final List<Question> questions = new ArrayList<>();
		final List<XWPFRun> runs = paragraph.getRuns();
		//We are not interesting in space lines
		if (runs.size() == 0) {
			return questions;
		}

		//We want only numeric lists
		final BigInteger paragraphNumerationLevel = paragraph.getNumIlvl();
		if (paragraphNumerationLevel == null) {
			return questions;
		}

		final String paragraphText = paragraph.getParagraphText();
		final String paragraphNumerationFormat = paragraph.getNumFmt();
		if (isQuestion(paragraphNumerationFormat)) {
			lastQuestion = new Question(getQuestionText(paragraphText), lastQuestion.getReferenceNumber() + 1);
			questions.add(lastQuestion);
		} else if (isAnswer(paragraphNumerationFormat)) {
			final boolean isCorrect = isCorrectAnswer(paragraphText);
			lastQuestion.addAnswer(getAnswerText(paragraphText), isCorrect);
			if (lastQuestion.getCorrectAnswersCount() > 1) {
				lastQuestion.setResponseType(ResponseType.MULTIPLE);
			}
		}

		return questions;
	}

	@Override
	public boolean isQuestion(String numFormat) {
		return QUESTION_NUM_FORMAT.equals(numFormat);
	}

	@Override
	public String getQuestionText(String text) {
		return text.trim();
	}

	@Override
	public boolean isAnswer(String numFormat) {
		return ANSWER_NUM_FORMAT.equals(numFormat);
	}

	@Override
	public String getAnswerText(String text) {
		return CORRECT_ANSWER_MARK_PATTERN.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim()).replaceFirst("");
	}

	@Override
	public boolean isCorrectAnswer(String text) {
		Matcher matcher = CORRECT_ANSWER_MARK_PATTERN.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim());
		return matcher.find() && matcher.start() == 0;
	}
}
