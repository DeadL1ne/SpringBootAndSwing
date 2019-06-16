package search.service;

import search.entity.Document;

import java.util.Set;

public interface IDocumentService extends IService {

    Set<Document> getDocumentsByKeyWord(String keyWord);

    void fillDatabase();

    Document saveDocument(Document document);

    Document getDocByName(String name);
}
