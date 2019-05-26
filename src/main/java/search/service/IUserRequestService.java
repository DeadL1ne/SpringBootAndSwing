package search.service;

import search.entity.UserRequest;

import java.util.Optional;

public interface IUserRequestService extends IService {

    Optional<UserRequest> getUserRequestByRequestText(String requestText);

    void save(UserRequest userRequest);
}
