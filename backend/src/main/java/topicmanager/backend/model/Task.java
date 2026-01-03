package topicmanager.backend.model;


import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.time.Instant;

@Entity
@Table(name = "task_table")
@Data
public class Task {
    @Id
    @Column(name = "id_title")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Status status;
    @Column
    private Instant createdAt;
}
