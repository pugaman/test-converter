package com.luxoft.converter.model.domain;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public class Answer {

	private final Integer order;
	private final String text;
	private final boolean isCorrect;

	public Answer(Integer order, String text, boolean isCorrect) {
		this.order = order;
		this.text = text;
		this.isCorrect = isCorrect;
	}

	public Integer getOrder() {
		return order;
	}

	public String getText() {
		return text;
	}

	public boolean isCorrect() {
		return isCorrect;
	}
}
