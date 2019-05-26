package search.service;

import error.NotFoundException;
import search.entity.User;

public interface IUserSevice {
    void addUser(User user) throws NotFoundException;
    Boolean isUserExists(String login, String password);
}
