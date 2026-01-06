package topicmanager.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import topicmanager.backend.dto.TaskCreateDto;
import topicmanager.backend.exception.TaskNotFoundException;
import topicmanager.backend.mapper.TaskMapperImpl;
import topicmanager.backend.model.Status;
import topicmanager.backend.model.Task;
import topicmanager.backend.repository.TaskRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapperImpl taskMapperImpl;

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


    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }


    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }


    public Task update(Long id, TaskCreateDto taskCreateDto) {
        Task task = findById(id);
        taskMapperImpl.updateEntityFromDto(taskCreateDto, task);
        return taskRepository.save(task);
    }
}
