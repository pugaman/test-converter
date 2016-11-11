package com.luxoft.converter.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class Question {

	private static final Random RANDOM = new Random();
	private static final Long LOWER_BOUND = 200000L;

	private final String text;
	private ResponseType responseType;
	private final Long referenceNumber;

	private final List<Answer> answers;

	public Question(String text, ResponseType responseType) {
		this.text = text;
		this.responseType = responseType;

		this.referenceNumber = RANDOM.nextLong() + LOWER_BOUND;

		this.answers = new ArrayList<>();
	}

	public Question(String text){
		this(text, ResponseType.SINGLE);
	}

	public String getText() {
		return text;
	}

	public ResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}

	public Long getReferenceNumber() {
		return referenceNumber;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public boolean addAnswer(String text, boolean isCorrect){
		Integer nextOrder = this.answers.size() + 1;
		Answer answer = new Answer(nextOrder, text, isCorrect);
		return this.answers.add(answer);
	}
}
