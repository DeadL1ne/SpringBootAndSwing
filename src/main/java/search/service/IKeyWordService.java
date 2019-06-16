package search.service;

import search.entity.KeyWord;

import java.util.List;

public interface IKeyWordService extends IService {
    List<KeyWord> getKeyWord(String keyWord);

    KeyWord saveKeyWord(KeyWord keyWord);


}
