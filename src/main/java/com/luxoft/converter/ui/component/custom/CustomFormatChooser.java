package com.luxoft.converter.ui.component.custom;

import com.luxoft.converter.service.format.TestParsingFormatHolder;
import com.luxoft.converter.service.test.parsing.TestParsingFormat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pavel on 14.11.2016.
 */
public class CustomFormatChooser extends JPanel implements ActionListener {

    private final JLabel title;
    private final JComboBox<TestParsingFormat> formatChooser;

    @Autowired
    TestParsingFormatHolder testParsingFormatHolder;

    public CustomFormatChooser() {
        super(new BorderLayout());

        this.title = new JLabel("Choose a test file format:");
        this.formatChooser = new JComboBox<>();
        installFormatChooser();

        buildComponent();
    }

    private void installFormatChooser() {
        TestParsingFormatHolder.getTestFileFormats().forEach(this.formatChooser::addItem);
        this.formatChooser.setRenderer(new ListCellRenderer<TestParsingFormat>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends TestParsingFormat> list, TestParsingFormat value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel(value.getFormatName());
            }
        });
        this.formatChooser.addActionListener(this);
    }

    private void buildComponent() {
        add(title, BorderLayout.PAGE_START);
        add(formatChooser, BorderLayout.LINE_START);
    }

    @PostConstruct
    protected void init(){
        this.formatChooser.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        testParsingFormatHolder.setTestParsingFormat((TestParsingFormat) cb.getSelectedItem());
    }
}
