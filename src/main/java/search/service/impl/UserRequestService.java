package search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import search.dao.UserRequestDAO;
import search.entity.User;
import search.entity.UserRequest;
import search.service.IUserRequestService;
import search.ui.AbstractForm;

import java.util.Optional;

@Service
public class UserRequestService implements IUserRequestService {

    private UserRequestDAO dao;
    private AbstractForm abstractForm;

    @Autowired
    public UserRequestService(UserRequestDAO dao) {
        this.dao = dao;
    }

    @Override
    public Optional<UserRequest> getUserRequestByRequestText(String requestText, User user) {
        return dao.findOne(Example.of(new UserRequest(requestText, user)));
    }

    @Override
    public void save(UserRequest userRequest) {
        dao.save(userRequest);
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
