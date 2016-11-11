package com.luxoft.converter.ui.component.action;

import com.luxoft.converter.model.domain.Question;
import com.luxoft.converter.service.test.constructing.DocumentConstructor;
import com.luxoft.converter.service.test.constructing.DocumentConstructorFactory;
import com.luxoft.converter.service.test.constructing.ms.excel.format.TestAnswersFormat;
import com.luxoft.converter.service.test.constructing.ms.excel.format.TestQuestionsFormat;
import com.luxoft.converter.service.test.parsing.DocumentParser;
import com.luxoft.converter.service.test.parsing.DocumentParserFactory;
import com.luxoft.converter.service.test.parsing.ms.word.DocXWordParser;
import com.luxoft.converter.service.file.VirtualFileStorage;
import com.luxoft.converter.service.test.parsing.ms.word.format.BabokTestFormat;
import com.luxoft.converter.service.test.parsing.ms.word.format.SimpleTestFormat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pavel on 10.11.2016.
 */
public class StartConvertingAction extends AbstractAction {

    @Autowired
    protected VirtualFileStorage virtualFileStorage;

    @Autowired
    protected DocumentParserFactory documentParserFactory;

    @Autowired
    protected DocumentConstructorFactory documentConstructorFactory;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        File test = new File("C:\\Users\\pgolovenkov\\Documents\\prj\\TestConverter\\BABOK-Quiz-Luxoft=Final=Parts 1-2-3-4.docx");

        DocumentParser parser = documentParserFactory.createParser(new SimpleTestFormat());
        List<Question> questions = parser.parse(test);

//        File questionsFile = new File("C:\\Users\\pgolovenkov\\Documents\\prj\\TestConverter\\Template_TestQuestionsTest.xls");
//        DocumentConstructor questionsConstructor = documentConstructorFactory.createConstructor(
//                new TestQuestionsFormat());
//        questionsConstructor.construct(questions, questionsFile);
//
//        File answersFile = new File("C:\\Users\\pgolovenkov\\Documents\\prj\\TestConverter\\Template_MultipleChoiceQuestionsTest.xls");
//        DocumentConstructor answersConstructor = documentConstructorFactory.createConstructor(new TestAnswersFormat());
//        answersConstructor.construct(questions, answersFile);


    }
}
