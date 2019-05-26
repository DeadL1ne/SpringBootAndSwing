package search.service.impl;

import error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import search.dao.UserDAO;
import search.entity.User;
import search.service.IUserSevice;

import java.util.Optional;

@Service
public class UserService implements IUserSevice {

    private final UserDAO dao;

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
}

