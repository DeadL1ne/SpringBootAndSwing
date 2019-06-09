package search.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_request")
public class UserRequest {

    @Id
    @GeneratedValue(generator = "user_request_generator")
    @SequenceGenerator(
            name = "user_request_generator",
            sequenceName = "user_request_sequence",
            initialValue = 1000
    )
    private Long id;

    private String requestText;

    @ManyToMany(fetch = FetchType.EAGER,
    cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "key_word_user_requests")
    private Set<KeyWord> keyWords = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserRequest(){}

    public UserRequest(String requestText) {
        this.requestText = requestText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    public Set<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Set<KeyWord> keyWords) {
        this.keyWords = keyWords;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
