package search.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import search.entity.Document;
import search.entity.KeyWord;

import java.util.List;

@Repository
public interface DocumentDAO extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d where d.name = :documentName")
    List<Document> findByDocumentName(@Param("documentName") String documentName);
}
