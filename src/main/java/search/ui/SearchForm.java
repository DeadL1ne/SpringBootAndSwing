package search.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import search.GlobalConst;
import search.entity.Document;
import search.entity.KeyWord;
import search.entity.UserRequest;
import search.service.impl.DocumentService;
import search.service.impl.KeyWordService;
import search.service.impl.UserRequestService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class SearchForm extends AbstractForm {

    @Autowired
    LoginForm loginForm;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserRequestService userRequestService;

    @Autowired
    private KeyWordService keyWordService;

    private UserRequest userRequest;

    private JPanel searchPanel;
    private JTextField searchTextField;
    private JButton buttonSearch;
    private JList<String> resultList;
    private JButton buttonLogOut;

    private DefaultListModel<String> resultDataset = new DefaultListModel<>();

    private Set<Document> docs = new HashSet<>();

    private Set<KeyWord> keyWords = new HashSet<>();

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
        resultList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList<String> theList = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    int index = theList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        doubleClickOnDocument(theList.getModel().getElementAt(index).toString());
                    }
                }
            }
        });
    }

    @Override
    void show() {
        super.show();
        documentService.attachForm(this);
        userRequestService.attachForm(this);
        keyWordService.attachForm(this);
        if(!GlobalConst.isDataBaseFilled) {
            new Thread(this::fillDataBase).start();
        }
    }

    @Override
    void hide() {
        super.hide();
        documentService.detachForm();
        userRequestService.detachForm();
        keyWordService.detachForm();
    }

    private void search() {
        if (searchTextField.getText().isEmpty()) return;

        docs.clear();
        resultDataset.clear();

        Optional<UserRequest> optionalUserRequest = userRequestService.getUserRequestByRequestText(searchTextField.getText(), loginForm.getUser());
        if (optionalUserRequest.isPresent()) {
            userRequest = optionalUserRequest.get();
            userRequest.setKeyWords(userRequest.getKeyWords());
            userRequest.getKeyWords().forEach(keyWord -> docs.addAll(documentService.getDocumentsByKeyWord(keyWord.getWord())));
        } else {
            List<String> splitedRequest = new ArrayList<>(Arrays.asList(searchTextField.getText()
                    .toLowerCase().split(" ")));
            splitedRequest.forEach(word -> {
                List<KeyWord> words = keyWordService.getKeyWord(word);
                if (!words.isEmpty()) {
                    keyWords.addAll(words);
                }
            });
            keyWords.forEach(keyWord -> docs.addAll(documentService.getDocumentsByKeyWord(keyWord.getWord())));
            saveUserRequest();
        }

        if (docs.isEmpty()) return;
        docs = docs.stream().filter(distinctByKey(Document::getName)).collect(Collectors.toSet());
        docs.forEach(document -> resultDataset.addElement(document.getName()));
    }

    private void saveUserRequest() {
        userRequest = new UserRequest(searchTextField.getText(), loginForm.getUser());
        userRequest.getKeyWords().addAll(keyWords);
        userRequestService.save(userRequest);
    }

    private void doubleClickOnDocument(String docName) {
        Document doc = documentService.getDocByName(docName);
        userRequest.getKeyWords().addAll(doc.getKeyWords().stream()
                .filter(distinctByKey(KeyWord::getWord)).collect(Collectors.toSet()));
        userRequestService.save(userRequest);
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private void mockData() {
        ArrayList<String> mockedData = new ArrayList<>();
        mockedData.add("File C#");
        mockedData.add("Java file");
        mockedData.add("Python file");
        mockedData.forEach(item -> resultDataset.addElement(item));
    }

    private void fillDataBase() {
        documentService.fillDatabase();
    }
}
