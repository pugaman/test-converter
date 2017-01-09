package com.luxoft.converter.service.test.parsing.ms.word.format.impl;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.model.domain.ResponseType;
import com.luxoft.converter.service.code.QuestionCodeProvider;
import com.luxoft.converter.service.test.parsing.ms.word.format.DocXTestParsingFormat;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class SimpleTestFormat implements DocXTestParsingFormat {

	private static final Pattern QUESTION_START_PATTERN = Pattern.compile("^[0-9]+\\)[\\s\\xA0]");
	private static final Pattern ANSWER_START_PATTERN = Pattern.compile("^[a-z]\\)[\\s\\xA0]");
	private static final Pattern CORRECT_ANSWER_MARK_PATTERN = Pattern.compile("[\\s\\xA0]\\+$");

	@Override
	public String getFormatName() {
		return "Simple format";
	}

	@Override
	public List<Question> analyzeParagraph(XWPFParagraph paragraph, Question lastQuestion, QuestionCodeProvider questionCodeProvider) {
		final List<Question> questions = new ArrayList<>();
		final List<XWPFRun> runs = paragraph.getRuns();
		//We are not interesting in space lines
		if (runs.size() == 0) {
			return questions;
		}

		StringBuilder textBuilder = new StringBuilder();
		Answer lastAnswer = null;

		//text parts analyzing
		for (XWPFRun run : runs) {
			final String runText = run.getText(0);

			if (isQuestion(runText)) {
				textBuilder = new StringBuilder(runText);
				lastAnswer = null;
				lastQuestion = new Question(getQuestionText(textBuilder.toString()), questionCodeProvider.getCode());
				questions.add(lastQuestion);
			} else if (isAnswer(runText)) {
				textBuilder = new StringBuilder(runText);
				final boolean isCorrect = isCorrectAnswer(runText);
				lastAnswer = lastQuestion.addAnswer(getAnswerText(textBuilder.toString()), isCorrect);
				if (lastQuestion.getCorrectAnswersCount() > 1) {
					lastQuestion.setResponseType(ResponseType.MULTIPLE);
				}
			} else if (lastAnswer != null) {
				textBuilder.append(runText);
				lastAnswer.setText(getAnswerText(textBuilder.toString()));
			} else {
				textBuilder.append(runText);
				lastQuestion.setText(getQuestionText(textBuilder.toString()));
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
		return QUESTION_START_PATTERN.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim()).replaceFirst("");
	}

	@Override
	public boolean isAnswer(String text) {
		Matcher matcher = ANSWER_START_PATTERN.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim());
		return matcher.find() && matcher.start() == 0;
	}

	@Override
	public String getAnswerText(String text) {
		String textWithoutPrefix = ANSWER_START_PATTERN.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim()).replaceFirst("");
		return CORRECT_ANSWER_MARK_PATTERN.matcher(textWithoutPrefix).replaceFirst("");
	}

	@Override
	public boolean isCorrectAnswer(String text) {
		Matcher matcher = CORRECT_ANSWER_MARK_PATTERN.matcher(text.replaceFirst(String.valueOf((char) 160), " ").trim());
		return matcher.find();
	}
}
