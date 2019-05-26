package search.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import search.entity.KeyWord;

import java.util.List;

@Repository
public interface KeyWordDAO extends JpaRepository<KeyWord, Long> {

    @Query("SELECT k FROM KeyWord k where k.word = :keyword")
    List<KeyWord> findByKeyWord(@Param("keyword") String keyword);

}
