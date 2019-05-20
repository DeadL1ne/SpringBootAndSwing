package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.dao.DocumentDAO;
import search.entity.Document;
import search.service.IDocumentService;

import java.util.HashSet;
import java.util.Set;

@Service
public class DocumentService implements IDocumentService {

    private DocumentDAO dao;
    private KeyWordService keyWordService;

    @Autowired
    public DocumentService(DocumentDAO dao, KeyWordService keyWordService) {

        this.dao = dao;
        this.keyWordService = keyWordService;
    }

    @Override
    public Set<Document> getDocumentsByKeyWord(String keyWord) {
        Set<Document> documents = new HashSet<>();
        keyWordService.getKeyWord(keyWord).forEach(word -> documents.addAll(word.getDocuments()));
        return documents;
    }
}
