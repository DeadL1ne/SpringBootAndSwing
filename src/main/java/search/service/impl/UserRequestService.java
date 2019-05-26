package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import search.dao.UserRequestDAO;
import search.entity.UserRequest;
import search.service.IUserRequestService;

import java.util.Optional;

@Service
public class UserRequestService implements IUserRequestService {

    private UserRequestDAO dao;

    @Autowired
    public UserRequestService(UserRequestDAO dao) {
        this.dao = dao;
    }

    @Override
    public Optional<UserRequest> getUserRequestByRequestText(String requestText) {
        return dao.findOne(Example.of(new UserRequest(requestText)));
    }

    @Override
    public void save(UserRequest userRequest) {
        dao.save(userRequest);
    }
}
