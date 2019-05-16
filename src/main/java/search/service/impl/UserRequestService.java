package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.dao.UserRequestDAO;
import search.service.IUserRequestService;

@Service
public class UserRequestService implements IUserRequestService {

    private UserRequestDAO dao;

    @Autowired
    public UserRequestService(UserRequestDAO dao) {
        this.dao = dao;
    }
}
