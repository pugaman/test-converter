package com.luxoft.converter.ui.component.action;

import com.luxoft.converter.service.file.VirtualFileStorage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Pavel on 10.11.2016.
 */
public class StartConvertingAction extends AbstractAction {

    @Autowired
    VirtualFileStorage virtualFileStorage;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JDialog dialog = new JDialog();
        dialog.setTitle("Chosen files");

        JLabel testLabel = new JLabel(virtualFileStorage.getFile(VirtualFileStorage.TEST_FILE).getName());
        dialog.getContentPane().add(testLabel);

        JLabel questionsLabel = new JLabel(virtualFileStorage.getFile(VirtualFileStorage.QUESTIONS_FILE).getName());
        dialog.getContentPane().add(questionsLabel);

        JLabel answersLabel = new JLabel(virtualFileStorage.getFile(VirtualFileStorage.ANSWERS_FILE).getName());
        dialog.getContentPane().add(answersLabel);

        dialog.setVisible(true);
    }
}
