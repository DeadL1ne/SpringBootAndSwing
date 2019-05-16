package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.dao.KeyWordDAO;
import search.service.IKeyWordService;

@Service
public class KeyWordService implements IKeyWordService {

    private KeyWordDAO dao;

    @Autowired
    public KeyWordService(KeyWordDAO dao) {
        this.dao = dao;
    }
}
