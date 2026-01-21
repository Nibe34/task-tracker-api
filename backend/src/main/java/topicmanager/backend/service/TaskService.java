package topicmanager.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topicmanager.backend.dto.TaskCreateDto;
import topicmanager.backend.dto.TaskFilterDto;
import topicmanager.backend.dto.TaskUpdateDto;
import topicmanager.backend.exception.EmptyPatchException;
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

    @Transactional
    public Task save(Task task) {
        task.setStatus(Status.TODO);
        return taskRepository.save(task);
    }


    @Transactional
    public void saveAll(List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> searchTasks(TaskFilterDto filter) {
        if (filter == null) {
            return taskRepository.findAll();
        }

        String titleParam = null;

        if (filter.title() != null && !filter.title().isBlank()) {
            titleParam = "%" + filter.title().toLowerCase() + "%";
        }

        return taskRepository.searchTasks(titleParam, filter.status());
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
    public Task patchTask(Long id, TaskUpdateDto dto) {
        Task task = findById(id);

        if (dto.title() == null && dto.description() == null) {
            throw new EmptyPatchException();
        }

        if (dto.title() != null) {
            task.changeTitle(dto.title());
        }

        if (dto.description() != null) {
            task.changeDescription(dto.description());
        }

        return taskRepository.save(task);
    }
}
