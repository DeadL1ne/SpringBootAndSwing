package search.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import search.entity.KeyWord;

@Repository
public interface KeyWordDAO extends JpaRepository<KeyWord, Long> {
}
