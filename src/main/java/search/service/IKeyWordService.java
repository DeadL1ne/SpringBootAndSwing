package search.service;

import search.entity.KeyWord;

import java.util.List;

public interface IKeyWordService {
    List<KeyWord> getKeyWord(String keyWord);
}
