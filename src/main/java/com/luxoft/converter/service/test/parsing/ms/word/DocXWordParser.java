package com.luxoft.converter.service.test.parsing.ms.word;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.model.domain.ResponseType;
import com.luxoft.converter.service.test.parsing.DocumentParser;
import com.luxoft.converter.service.test.parsing.ParsingFormat;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgolovenkov on 09.11.2016.
 */
public class DocXWordParser implements DocumentParser{

	private final ParsingFormat format;

	public DocXWordParser(ParsingFormat format) {
		this.format = format;
	}

	public List<Question> parse(File file) {

		final List<Question> questions = new ArrayList<>();

		try (FileInputStream inputStream = new FileInputStream(file);
			 XWPFDocument document = new XWPFDocument(inputStream)) {

			Question lastQuestion = null;
			int correctAnswersCount = 0;

			final List<IBodyElement> elements = document.getBodyElements();
			for (final IBodyElement element : elements) {
				//We are not interesting in Tables and Contents
				if(!(element instanceof XWPFParagraph)) {
					continue;
				}

				final XWPFParagraph paragraph = (XWPFParagraph)element;
				final List<XWPFRun> runs = paragraph.getRuns();
				//We are not interesting in space lines
				if(runs.size() == 0){
					continue;
				}

				String text = "";
				runs.forEach(r -> text.concat(r.getText(0)));

				if(format.isQuestion(text)){
					lastQuestion = new Question(text);
					correctAnswersCount = 0;
					questions.add(lastQuestion);
				} else if(format.isAnswer(text)){
					final boolean isCorrect = format.isCorrectAnswer(text);
					if(lastQuestion != null) {
						lastQuestion.addAnswer(text, isCorrect);
						if (isCorrect) {
							correctAnswersCount++;
						}
						if (correctAnswersCount > 1) {
							lastQuestion.setResponseType(ResponseType.MULTIPLE);
						}
					}
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}

		return questions;
	}


}
