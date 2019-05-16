package search.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import search.entity.Document;

@Repository
public interface DocumentDAO extends JpaRepository<Document, Long> {
}
