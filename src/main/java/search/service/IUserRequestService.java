package search.service;

import search.entity.User;
import search.entity.UserRequest;

import java.util.Optional;

public interface IUserRequestService extends IService {

    Optional<UserRequest> getUserRequestByRequestText(String requestText, User user);

    void save(UserRequest userRequest);
}
