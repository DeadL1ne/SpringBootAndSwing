package search.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Component
public class SearchForm extends AbstractForm {

    @Autowired
    LoginForm loginForm;

    private JPanel searchPanel;
    private JTextField searchTextField;
    private JButton buttonSearch;
    private JList resultList;
    private JButton buttonLogOut;

    private DefaultListModel<String> resultDataset = new DefaultListModel<>();

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

        buttonSearch.addActionListener(e -> mockData());
        buttonLogOut.addActionListener(e -> {
            hide();
            resultDataset.clear();
            loginForm.show();
        });
    }

    private void mockData() {
        ArrayList<String> mockedData = new ArrayList<>();
        mockedData.add("File C#");
        mockedData.add("Java file");
        mockedData.add("Python file");
        setDataToList(mockedData);
    }

    private void setDataToList(ArrayList<String> data) {
        for (String item : data) {
            resultDataset.addElement(item);
        }
    }
}
