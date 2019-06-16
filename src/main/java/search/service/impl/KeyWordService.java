package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import search.dao.KeyWordDAO;
import search.entity.KeyWord;
import search.service.IKeyWordService;
import search.ui.AbstractForm;

import java.util.List;

@Service
public class KeyWordService implements IKeyWordService {

    private KeyWordDAO dao;
    private AbstractForm abstractForm;

    @Autowired
    public KeyWordService(KeyWordDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<KeyWord> getKeyWord(String keyWord) {
        return dao.findAll(Example.of(new KeyWord(keyWord)));
    }

    @Override
    public KeyWord saveKeyWord(KeyWord keyWord) {
        return dao.save(keyWord);
    }

    @Override
    public void attachForm(AbstractForm form) {
        this.abstractForm = form;
    }

    @Override
    public void detachForm() {
        this.abstractForm = null;
    }
}
