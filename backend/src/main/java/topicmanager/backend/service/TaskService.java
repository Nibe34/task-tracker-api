package topicmanager.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topicmanager.backend.dto.TaskCreateDto;
import topicmanager.backend.exception.TaskNotFoundException;
import topicmanager.backend.mapper.TaskMapperImpl;
import topicmanager.backend.model.Status;
import topicmanager.backend.model.Task;
import topicmanager.backend.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapperImpl taskMapperImpl;

    public Task save(Task task) {
        task.setStatus(Status.TODO);
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
                .orElseThrow(() -> new TaskNotFoundException(id));
    }


    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }


    @Transactional
    public Task update(Long id, TaskCreateDto taskCreateDto) {
        Task task = findById(id);
        taskMapperImpl.updateEntityFromDto(taskCreateDto, task);
        return taskRepository.save(task);
    }


    @Transactional
    public Task updateStatus(Long taskId, Status newStatus) {
        Task task = findById(taskId);
        task.changeStatus(newStatus);
        return taskRepository.save(task);
    }


    @Transactional
    public Task updateTitle(Long taskId, String newTitle) {
        Task task = findById(taskId);
        task.changeTitle(newTitle);
        return taskRepository.save(task);
    }


    @Transactional
    public Task updateDescription(Long taskId, String newDescription) {
        Task task = findById(taskId);
        task.changeDescription(newDescription);
        return taskRepository.save(task);
    }
}
