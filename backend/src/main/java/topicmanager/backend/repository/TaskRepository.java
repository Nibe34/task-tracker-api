package topicmanager.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import topicmanager.backend.model.Status;
import topicmanager.backend.model.Task;

import java.time.Instant;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE" +
            "(:title IS NULL OR LOWER(t.title) LIKE :title)" +
            " AND " +
            "(:status IS NULL OR :status = t.status)"
    )
    Page<Task> searchTasks(@Param("title") String title,
                           @Param("status")Status status,
                           Pageable pageable);



    // ТІЛЬКИ ДЛЯ ТЕСТІВ
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.createdAt = :date WHERE t.id = :id")
    void forceUpdateCreatedAt(@Param("id") Long id, @Param("date") Instant date);
}
