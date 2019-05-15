package search.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userr")
public class Userr {

    @Id
    @GeneratedValue(generator = "userr_generator")
    @SequenceGenerator(
            name = "userr_generator",
            sequenceName = "userr_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(unique = true)
    private String login;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserRequest> requests = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<UserRequest> items) {
        this.requests = items;
    }
}
