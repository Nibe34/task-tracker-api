package topicmanager.backend.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import topicmanager.backend.exception.CannotEditTaskInCurrentStatusException;
import topicmanager.backend.exception.InvalidTaskStatusTransitionException;

import java.time.Instant;

@Entity
@Table(name = "task_table")
@Data
public class Task {
    @Id
    @Column(name = "id_title")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @Column
    private Instant completedAt;




    public static Task create(String title, String description) {
        Task task = new Task();
        task.title = title;
        task.description = description;
        task.status = Status.TODO;
        return task;
    }


    public void changeStatus(Status newStatus) {
        if (newStatus == this.status) {
            return;
        }

        if (!this.status.isValidTransition(newStatus)) {
            throw new InvalidTaskStatusTransitionException(this.status, newStatus);
        }

        this.status = newStatus;

        if (newStatus == Status.DONE && this.completedAt == null) {
            this.completedAt = Instant.now();
        }
    }




    public void changeTitle(String newTitle) {
        if (!this.status.isEditable()) {
            throw new CannotEditTaskInCurrentStatusException(this.id);
        }
        this.title = newTitle;
    }




    public void changeDescription(String newDescription) {
        if (!this.status.isEditable()) {
            throw new CannotEditTaskInCurrentStatusException(this.id);
        }
        this.description = newDescription;
    }
}
