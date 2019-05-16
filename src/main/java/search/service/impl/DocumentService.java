package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.dao.DocumentDAO;
import search.service.IDocumentService;

@Service
public class DocumentService implements IDocumentService {

    private DocumentDAO dao;

    @Autowired
    public DocumentService(DocumentDAO dao) {
        this.dao = dao;
    }
}
