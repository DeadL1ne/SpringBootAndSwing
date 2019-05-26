package search.service;

import search.ui.AbstractForm;

public interface IService {

    void attachForm(AbstractForm form);

    void detachForm();
}
