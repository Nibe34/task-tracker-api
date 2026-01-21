package topicmanager.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import topicmanager.backend.model.Status;
import topicmanager.backend.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE" +
            "(:title IS NULL OR LOWER(t.title) LIKE :title)" +
            " AND " +
            "(:status IS NULL OR :status = t.status)"
    )
    List<Task> searchTasks(@Param("title") String title, @Param("status")Status status);
}
