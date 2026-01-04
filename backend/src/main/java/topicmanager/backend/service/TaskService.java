package topicmanager.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import topicmanager.backend.model.Status;
import topicmanager.backend.model.Task;
import topicmanager.backend.repository.TaskRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task save(Task task) {
        task.setStatus(Status.PENDING);
        task.setCreatedAt(Instant.now());
        return taskRepository.save(task);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }


    public void saveAll(List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }
}
