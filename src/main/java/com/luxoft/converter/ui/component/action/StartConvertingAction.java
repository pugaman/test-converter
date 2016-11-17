package com.luxoft.converter.ui.component.action;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.service.file.VirtualFileStorage;
import com.luxoft.converter.service.format.TestParsingFormatHolder;
import com.luxoft.converter.service.test.constructing.DocumentConstructor;
import com.luxoft.converter.service.test.constructing.DocumentConstructorFactory;
import com.luxoft.converter.service.test.parsing.DocumentParser;
import com.luxoft.converter.service.test.parsing.DocumentParserFactory;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.luxoft.converter.service.test.constructing.DocumentConstructor.ANSWERS_FILE_NAME_SUFFIX;
import static com.luxoft.converter.service.test.constructing.DocumentConstructor.QUESTIONS_FILE_NAME_SUFFIX;

/**
 * Created by Pavel on 10.11.2016.
 */
public class StartConvertingAction extends AbstractAction implements PropertyChangeListener {

	private static final String TASK_PROGRESS_PROPERTY_NAME = "progress";

	@Autowired
	protected TestParsingFormatHolder testParsingFormatHolder;

	@Autowired
	protected VirtualFileStorage virtualFileStorage;

	@Autowired
	protected DocumentParserFactory documentParserFactory;

	@Autowired
	protected DocumentConstructorFactory documentConstructorFactory;

	@Override
	public void actionPerformed(ActionEvent e) {
		Component parent = SwingUtilities.getWindowAncestor((Component) e.getSource());

		//Check format
		if (!documentParserFactory.isSupport(testParsingFormatHolder.getTestParsingFormat())) {
			JOptionPane.showMessageDialog(parent, "Chosen format is not supported.", "Format error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		//Check if files exist and chosen
		if (!virtualFileStorage.isValid()) {
			JOptionPane
					.showMessageDialog(parent, "Please, choose all files.", "File error", JOptionPane.WARNING_MESSAGE);
			return;
		}


		SwingWorker task = new ConvertingTask();
		task.addPropertyChangeListener(this);
		task.execute();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (TASK_PROGRESS_PROPERTY_NAME.equals(evt.getPropertyName())) {
			int progress = (Integer) evt.getNewValue();
		}
	}

	private class ConvertingTask extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			setEnabled(false);
			//Start processing
			final DocumentParser parser = documentParserFactory
					.createParser(testParsingFormatHolder.getTestParsingFormat());
			final File testSourceFile = virtualFileStorage.getFile(VirtualFileStorage.TEST_FILE);
			final List<Question> questions = parser.parse(testSourceFile);

			final File resultsDirectory = virtualFileStorage.getFile(VirtualFileStorage.RESULTS_FILE);

			final File questionsTargetFile = virtualFileStorage.getFile(VirtualFileStorage.QUESTIONS_FILE);
			try (DocumentConstructor xlsQuestionsConstructor = documentConstructorFactory
					.createConstructor(questionsTargetFile, resultsDirectory,
							FilenameUtils.getBaseName(testSourceFile.getName()) + QUESTIONS_FILE_NAME_SUFFIX)) {
				questions.forEach(xlsQuestionsConstructor::writeQuestion);
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}

			final File answersTargetFile = virtualFileStorage.getFile(VirtualFileStorage.ANSWERS_FILE);
			try (DocumentConstructor xlsAnswersConstructor = documentConstructorFactory
					.createConstructor(answersTargetFile, resultsDirectory,
							FilenameUtils.getBaseName(testSourceFile.getName()) + ANSWERS_FILE_NAME_SUFFIX)) {
				for (Question question : questions) {
					for (Answer answer : question.getAnswers()) {
						xlsAnswersConstructor.writeAnswer(answer, question);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			return null;
		}

		@Override
		protected void done() {
			setEnabled(true);
			JOptionPane
					.showMessageDialog(null, "Converting is completed.", "Complete", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
