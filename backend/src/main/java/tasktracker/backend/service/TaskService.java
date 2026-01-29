package tasktracker.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tasktracker.backend.dto.TaskCreateDto;
import tasktracker.backend.dto.TaskFilterDto;
import tasktracker.backend.dto.TaskUpdateDto;
import tasktracker.backend.exception.EmptyPatchException;
import tasktracker.backend.exception.InvalidSortDirectionException;
import tasktracker.backend.exception.InvalidSortFieldException;
import tasktracker.backend.exception.TaskNotFoundException;
import tasktracker.backend.mapper.TaskMapper;
import tasktracker.backend.model.Status;
import tasktracker.backend.model.Task;
import tasktracker.backend.repository.TaskRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id", "title", "description", "status", "createdAt", "completedAt"
    );

    @Transactional
    public Task save(TaskCreateDto dto) {
        Task task = Task.create(dto.title(), dto.description());
        return taskRepository.save(task);
    }


    @Transactional
    public void saveAll(List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Page<Task> searchTasks(TaskFilterDto filter, int page, int size, String sortDir, String sortField) {

        if (!ALLOWED_SORT_FIELDS.contains(sortField)) {
            throw new InvalidSortFieldException(sortField, ALLOWED_SORT_FIELDS);
        }

        Sort.Direction sortDirection;
        try {
            sortDirection = Sort.Direction.fromString(sortDir);
        } catch (IllegalArgumentException e) {
            throw new InvalidSortDirectionException(sortDir);
        }


        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

        if (filter == null) {
            return taskRepository.findAll(pageable);
        }

        String titleParam = null;
        if (filter.title() != null && !filter.title().isBlank()) {
            titleParam = "%" + filter.title().toLowerCase() + "%";
        }

        return taskRepository.searchTasks(titleParam, filter.status(), pageable);
    }


    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }


    @Transactional
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }

/*
// TODO: наразі не використовується

    @Transactional
    public Task update(Long id, TaskCreateDto taskCreateDto) {
        Task task = findById(id);
        taskMapper.updateEntityFromDto(taskCreateDto, task);
        return taskRepository.save(task);
    }
*/

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
