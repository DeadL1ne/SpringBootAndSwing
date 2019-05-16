package search.service;

import search.entity.User;

public interface IUserSevice {
    void addUser(User user);
    Boolean isUserExists(String login, String password);
}
