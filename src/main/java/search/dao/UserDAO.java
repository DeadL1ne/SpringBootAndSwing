package search.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import search.entity.Userr;

@Repository
public interface UserDAO extends JpaRepository<Userr, Long> {
}
