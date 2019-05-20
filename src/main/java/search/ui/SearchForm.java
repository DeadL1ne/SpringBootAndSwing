package search.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import search.entity.Document;
import search.service.impl.DocumentService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class SearchForm extends AbstractForm {

    @Autowired
    LoginForm loginForm;

    @Autowired
    private DocumentService documentService;

    private JPanel searchPanel;
    private JTextField searchTextField;
    private JButton buttonSearch;
    private JList<String> resultList;
    private JButton buttonLogOut;

    private DefaultListModel<String> resultDataset = new DefaultListModel<>();

    Set<Document> docs = new HashSet<>();

    public SearchForm() {
        final int WIDTH_FORM = 500;
        final int HEIGHT_FORM = 500;
        final String TITLE_FORM = "Search";
        frame = new JFrame(TITLE_FORM);

        frame.setContentPane(searchPanel);
        frame.setPreferredSize(new Dimension(WIDTH_FORM, HEIGHT_FORM));
        frame.setSize(WIDTH_FORM, HEIGHT_FORM);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFrameToCenterScreen();
        frame.pack();
        resultList.setModel(resultDataset);

        buttonSearch.addActionListener(e -> search());
        buttonLogOut.addActionListener(e -> {
            hide();
            resultDataset.clear();
            loginForm.show();
        });
    }

    private void search() {
        if (searchTextField.getText().isEmpty()) return;

        docs.clear();
        resultDataset.clear();
        ArrayList<String> keyWords = new ArrayList<>(Arrays.asList(searchTextField.getText()
                .toLowerCase().split(" ")));
        keyWords.forEach(keyWord -> docs.addAll(documentService.getDocumentsByKeyWord(keyWord)));

        if (docs.isEmpty()) return;
        docs.forEach(document -> resultDataset.addElement(document.getName()));
    }

    private void mockData() {
        ArrayList<String> mockedData = new ArrayList<>();
        mockedData.add("File C#");
        mockedData.add("Java file");
        mockedData.add("Python file");
        mockedData.forEach(item -> resultDataset.addElement(item));
    }
}
