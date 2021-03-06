package search.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "key_word")
public class KeyWord {

    @Id
    @GeneratedValue(generator = "key_word_generator")
    @SequenceGenerator(
            name = "key_word_generator",
            sequenceName = "key_word_sequence",
            initialValue = 1000
    )
    private Long id;

    @NaturalId
    @Column(unique = true)
    private String word;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "keyWords")
    private Set<Document> documents = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "keyWords")
    private Set<UserRequest> userRequests = new HashSet<>();

    public KeyWord(){}

    public KeyWord(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<UserRequest> getUserRequests() {
        return userRequests;
    }

    public void setUserRequests(Set<UserRequest> userRequests) {
        this.userRequests = userRequests;
    }
}
