package search.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import search.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
}
