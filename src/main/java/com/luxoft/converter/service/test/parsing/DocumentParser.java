package com.luxoft.converter.service.test.parsing;

import com.luxoft.converter.model.domain.Question;

import java.io.File;
import java.util.List;

/**
 * Created by pgolovenkov on 11.11.2016.
 */
public interface DocumentParser {

	List<Question> parse(File file);
}
