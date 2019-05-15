package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.dao.UserDAO;
import search.entity.Userr;
import search.service.IUserSevice;

@Service
public class UserService implements IUserSevice {

    private final UserDAO dao;

    @Autowired
    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public void addUser(Userr user) {
        dao.save(user);
    }
}
