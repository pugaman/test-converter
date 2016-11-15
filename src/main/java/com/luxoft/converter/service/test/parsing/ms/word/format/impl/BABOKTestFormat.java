package com.luxoft.converter.service.test.parsing.ms.word.format.impl;

import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.model.domain.ResponseType;
import com.luxoft.converter.service.test.parsing.ms.word.format.DocXTestParsingFormat;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pavel on 10.11.2016.
 */
public class BABOKTestFormat implements DocXTestParsingFormat {

	private static final Pattern QUESTION_START_PATTERN = Pattern.compile("^[0-9]{1,2}\\-[0-9]{3}[\\s\\xA0]");
	private static final String ANSWER_NUM_FORMAT = "lowerLetter";
	private static final Pattern CORRECT_ANSWER_MARK_PATTERN = Pattern.compile("^Correct\\.[\\s\\xA0]");
	private static final Pattern INCORRECT_ANSWER_MARK_PATTERN = Pattern.compile("^Incorrect\\.[\\s\\xA0]");

	private Integer processedAnswerNumber = 0;

	@Override
	public String getFormatName() {
		return "BABOK format";
	}

	@Override
	public List<Question> analyzeParagraph(XWPFParagraph paragraph, Question lastQuestion) {
		final List<Question> questions = new ArrayList<>();
		final List<XWPFRun> runs = paragraph.getRuns();
		//We are not interesting in space lines
		if (runs.size() == 0) {
			return questions;
		}

		final String paragraphText = paragraph.getParagraphText();
		final String paragraphNumerationFormat = paragraph.getNumFmt();
		if (isQuestion(paragraphText)) {
			lastQuestion = new Question(getQuestionText(paragraphText), lastQuestion.getReferenceNumber() + 1);
			questions.add(lastQuestion);
			processedAnswerNumber = -1;
		} else if (isAnswer(paragraphNumerationFormat)) {
			boolean isCorrect = isCorrectAnswer(paragraphText);
			boolean isIncorrect = INCORRECT_ANSWER_MARK_PATTERN
					.matcher(paragraphText.replaceFirst(String.valueOf((char) 160), " ").trim()).find();
			if (isCorrect || isIncorrect) {
				processedAnswerNumber++;
				if (isCorrect) {
					lastQuestion.makeAnswerCorrect(processedAnswerNumber);
					if (lastQuestion.getCorrectAnswersCount() > 1) {
						lastQuestion.setResponseType(ResponseType.MULTIPLE);
					}
				}
			} else {
				lastQuestion.addAnswer(getAnswerText(paragraphText), false);
			}
		}

		return questions;
	}

	@Override
	public boolean isQuestion(String text) {
		Matcher matcher = QUESTION_START_PATTERN.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim());
		return matcher.find() && matcher.start() == 0;
	}

	@Override
	public String getQuestionText(String text) {
		return QUESTION_START_PATTERN.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim())
				.replaceFirst("");
	}

	@Override
	public boolean isAnswer(String numFormat) {
		return ANSWER_NUM_FORMAT.equals(numFormat);
	}

	@Override
	public String getAnswerText(String text) {
		return text.trim();
	}

	@Override
	public boolean isCorrectAnswer(String text) {
		Matcher matcher = CORRECT_ANSWER_MARK_PATTERN
				.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim());
		return matcher.find() && matcher.start() == 0;
	}
}
