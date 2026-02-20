package tasktracker.backend.user.internal.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "user_table")
@Data
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Instant createdAt;




    public static User create(String username, String email) {
        User user = new User();
        user.username = username;
        user.email = email;
        return user;
    }




    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }


    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }
}
