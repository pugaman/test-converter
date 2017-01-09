package com.luxoft.converter.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class Question {

	private String text;
	private ResponseType responseType;
	private Integer correctAnswersCount;
	private final String referenceCode;

	private final List<Answer> answers;

	private Question(String text, String referenceCode, ResponseType responseType) {
		this.text = text;
		this.responseType = responseType;
		this.correctAnswersCount = 0;

		this.referenceCode = referenceCode;

		this.answers = new ArrayList<>();
	}

	public Question(String text, String referenceCode) {
		this(text, referenceCode, ResponseType.SINGLE);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}

	public Integer getCorrectAnswersCount() {
		return correctAnswersCount;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public Answer addAnswer(String text, boolean isCorrect) {
		final Integer nextOrder = answers.size() + 1;
		final Answer answer = new Answer(nextOrder, text, isCorrect);
		answers.add(answer);
		if (isCorrect) {
			correctAnswersCount++;
		}
		return answer;
	}

	public void makeAnswerCorrect(int order) {
		if (answers.size() >= order + 1){
			Answer answer = answers.get(order);
			if(!answer.isCorrect()){
				answer.setIsCorrect(true);
				correctAnswersCount++;
			}
		}
	}
}
