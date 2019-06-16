package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import search.GlobalConst;
import search.dao.DocumentDAO;
import search.entity.Document;
import search.entity.KeyWord;
import search.helpers.CsvHelper;
import search.service.IDocumentService;
import search.ui.AbstractForm;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DocumentService implements IDocumentService {

    private DocumentDAO dao;
    private KeyWordService keyWordService;
    private CsvHelper csvHelper;
    private AbstractForm abstractForm;

    @Autowired
    public DocumentService(DocumentDAO dao, KeyWordService keyWordService, CsvHelper csvHelper) {
        this.dao = dao;
        this.keyWordService = keyWordService;
        this.csvHelper = csvHelper;
    }

    @Override
    public Set<Document> getDocumentsByKeyWord(String keyWord) {
        Set<Document> documents = new HashSet<>();
        keyWordService.getKeyWord(keyWord).forEach(word -> documents.addAll(word.getDocuments()));
        return documents;
    }

    @Override
    public void fillDatabase() {
        try {
            Map<String, List<String>> fileKeys = csvHelper.getCsvData();
            for (String filename : fileKeys.keySet()) {
                Set<KeyWord> keyWords = new HashSet<>();
                for (String keyWord : fileKeys.get(filename)) {
                    if (keyWordService.getKeyWord(keyWord).isEmpty()) {
                        KeyWord savedKeyWord = new KeyWord();
                        savedKeyWord.setWord(keyWord);
                        keyWords.add(keyWordService.saveKeyWord(savedKeyWord));
                    } else {
                        keyWords.add(keyWordService.getKeyWord(keyWord).get(0));
                    }
                }
                Document document;
                if(getDocByName(filename) == null) {
                    document = new Document();
                } else {
                    document = getDocByName(filename);
                }
                document.setName(filename);
                document.setKeyWords(keyWords);
                saveDocument(document);
            }
            GlobalConst.isDataBaseFilled = true;
        } catch (IOException ex) {
            abstractForm.showError("Filling to Database", ex.getMessage());
            GlobalConst.isDataBaseFilled = false;
        }
    }

    @Override
    public Document saveDocument(Document document) {
        return dao.save(document);
    }

    @Override
    public void attachForm(AbstractForm form) {
        this.abstractForm = form;
    }

    @Override
    public void detachForm() {
        this.abstractForm = null;
    }

    @Override
    public Document getDocByName(String name) {
         return dao.findOne(Example.of(new Document(name))).orElse(new Document(name));
    }
}
