package topicmanager.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import topicmanager.backend.model.Task;
import topicmanager.backend.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public void save(Task task) {
        taskRepository.save(task);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }


    public void saveAll(List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }
}
