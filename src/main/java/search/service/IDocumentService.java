package search.service;

import search.entity.Document;

import java.util.Set;

public interface IDocumentService {

    Set<Document> getDocumentsByKeyWord(String keyWord);
}
