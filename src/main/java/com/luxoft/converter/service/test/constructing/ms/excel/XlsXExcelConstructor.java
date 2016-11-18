package com.luxoft.converter.service.test.constructing.ms.excel;

import com.luxoft.converter.model.domain.Answer;
import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.service.test.constructing.DocumentConstructor;
import com.luxoft.converter.service.test.constructing.listener.DocumentConstructorListener;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.luxoft.converter.model.domain.ResponseType.MULTIPLE;
import static org.apache.commons.io.FilenameUtils.EXTENSION_SEPARATOR_STR;
import static org.apache.commons.io.FilenameUtils.getExtension;

/**
 * Created by Pavel on 10.11.2016.
 */
public class XlsXExcelConstructor implements DocumentConstructor {

	private static final short STARTING_ROW = 1;
	private static final String SINGLE_RESPONSE_TYPE_VALUE = "4";
	private static final String MULTIPLE_RESPONSE_TYPE_VALUE = "5";
	private static final String CULTURE_ID = "en-US";

	private final File result;
	private final Workbook workbook;
	private final CreationHelper creationHelper;
	private short currentRow = STARTING_ROW;

	XlsXExcelConstructor(File template, File targetDirectory, String resultsFileName)
			throws IOException, InvalidFormatException {
		resultsFileName = resultsFileName + EXTENSION_SEPARATOR_STR + getExtension(template.getName());
		this.result = new File(targetDirectory, resultsFileName);

		InputStream inp = new FileInputStream(template);
		this.workbook = WorkbookFactory.create(inp);
		this.creationHelper = this.workbook.getCreationHelper();
	}

	@Override
	public void writeQuestion(Question question) {
		Sheet activeSheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
		Row row = activeSheet.createRow(currentRow);
		row.createCell(0).setCellValue(question.getReferenceNumber().toString());
		row.createCell(1).setCellValue(creationHelper.createRichTextString(question.getText()));
		row.createCell(2).setCellValue(MULTIPLE.equals(
				question.getResponseType()) ? MULTIPLE_RESPONSE_TYPE_VALUE : SINGLE_RESPONSE_TYPE_VALUE);
		row.createCell(6).setCellValue(creationHelper.createRichTextString("Y"));
		row.createCell(8).setCellValue(creationHelper.createRichTextString(CULTURE_ID));
		row.createCell(9).setCellValue(creationHelper.createRichTextString(CULTURE_ID));
		row.createCell(10).setCellValue(creationHelper.createRichTextString("Y"));
		row.createCell(11)
				.setCellValue(creationHelper.createRichTextString(String.valueOf(question.getAnswers().size())));
		row.createCell(13).setCellValue("1");
		currentRow++;
	}

	@Override
	public void writeAnswer(Answer answer, Question question) {
		Sheet activeSheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
		Row row = activeSheet.createRow(currentRow);
		row.createCell(0).setCellValue(question.getReferenceNumber());
		row.createCell(1).setCellValue(answer.getOrder());
		row.createCell(2).setCellValue(creationHelper.createRichTextString(answer.getText()));
		row.createCell(3).setCellValue(answer.isCorrect() ? creationHelper.createRichTextString("Y") : creationHelper
				.createRichTextString("N"));
		row.createCell(4).setCellValue(creationHelper.createRichTextString("Y"));
		row.createCell(5).setCellValue(creationHelper.createRichTextString(CULTURE_ID));
		currentRow++;
	}

	@Override
	public void addListener(DocumentConstructorListener listener) {

	}

	@Override
	public void close() throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(result);
		workbook.write(fileOutputStream);
		workbook.close();
		fileOutputStream.close();
	}
}
