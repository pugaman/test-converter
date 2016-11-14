package com.luxoft.converter.service.test.constructing;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.service.test.constructing.listener.DocumentConstructorListener;

import java.io.Closeable;
import java.io.File;
import java.util.List;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public interface DocumentConstructor extends Closeable{

	void writeQuestion(Question question);

	void writeAnswer(Answer answer, Question question);

	void addListener(DocumentConstructorListener listener);

}
