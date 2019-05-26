package search.service;

import search.entity.UserRequest;

import java.util.Optional;

public interface IUserRequestService {

    Optional<UserRequest> getUserRequestByRequestText(String requestText);

    void save(UserRequest userRequest);
}
