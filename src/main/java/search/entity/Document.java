package search.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "document")
public class Document {

    public Document() {}

    public Document(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(generator = "document_generator")
    @SequenceGenerator(
            name = "document_generator",
            sequenceName = "document_sequence",
            initialValue = 1000
    )
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "key_word_documents")
    private Set<KeyWord> keyWords = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Set<KeyWord> keyWords) {
        this.keyWords = keyWords;
    }
}
