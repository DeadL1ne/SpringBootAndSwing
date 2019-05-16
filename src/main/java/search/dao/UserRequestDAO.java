package search.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import search.entity.UserRequest;

@Repository
public interface UserRequestDAO extends JpaRepository<UserRequest, Long> {
}
