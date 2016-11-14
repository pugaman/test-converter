package com.luxoft.converter.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class Question {

	private String text;
	private ResponseType responseType;
	private final Integer referenceNumber;

	private final List<Answer> answers;

	public Question(String text, Integer number, ResponseType responseType) {
		this.text = text;
		this.responseType = responseType;

		this.referenceNumber = number;

		this.answers = new ArrayList<>();
	}

	public Question(String text, Integer number){
		this(text, number, ResponseType.SINGLE);
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

	public Integer getReferenceNumber() {
		return referenceNumber;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public Answer addAnswer(String text, boolean isCorrect){
		final Integer nextOrder = answers.size() + 1;
		final Answer answer = new Answer(nextOrder, text, isCorrect);
		answers.add(answer);
		return answer;
	}
}
