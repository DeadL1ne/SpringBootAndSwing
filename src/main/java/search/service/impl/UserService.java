package search.service.impl;

import error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import search.dao.UserDAO;
import search.entity.User;
import search.service.IUserService;
import search.ui.AbstractForm;

@Service
public class UserService implements IUserService {

    private final UserDAO dao;
    private AbstractForm abstractForm;

    @Autowired
    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public void addUser(User user) {
        dao.save(user);
    }

    @Override
    public Boolean isUserExists(String login, String password) {
        return dao.exists(Example.of(new User(login, password)));
    }

    public User getUser(String login, String password) throws NotFoundException {
        return dao.findOne(Example.of(new User(login, password))).orElseThrow(() -> new NotFoundException());
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

