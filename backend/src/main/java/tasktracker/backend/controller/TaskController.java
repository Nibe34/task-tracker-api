package tasktracker.backend.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tasktracker.backend.dto.*;
import tasktracker.backend.mapper.TaskMapper;
import tasktracker.backend.model.Task;
import tasktracker.backend.service.TaskService;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    @PostMapping()
    public TaskResponseDto createTask(@RequestBody @Valid TaskCreateDto taskCreateDto) {
        Task savedTask = taskService.save(taskCreateDto);
        return taskMapper.toDto(savedTask);
    }


    @GetMapping()
    public Page<TaskResponseDto> getAllTasks(
            @ModelAttribute TaskFilterDto filter,

            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page number must be 0 or greater")
            int page,

            @RequestParam(defaultValue = "5")
            @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 20, message = "Page size must mot exceed 20")
            int size,
            @RequestParam(defaultValue = "ASC") String sortDir,
            @RequestParam(defaultValue = "id") String sortField
    ) {
        Page<Task> taskPage = taskService.searchTasks(filter, page, size, sortDir, sortField);
        return taskPage.map(taskMapper::toDto);
    }


    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return taskMapper.toDto(task);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }

/*
// TODO: оскільки є patch, то поки цей функціонал лише порушує логіку системи обходячи доменні правила.
         в майбутньому можливо видалити чи зробити доступним лише для адміністраторів

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id, @RequestBody @Valid TaskCreateDto taskCreateDto) {
        Task updatedTask = taskService.update(id, taskCreateDto);
        return taskMapper.toDto(updatedTask);
    }
*/

    @PatchMapping("/{id}/status")
    public TaskResponseDto updateTaskStatus(@PathVariable Long id, @RequestBody @Valid TaskStatusUpdateDto taskStatusUpdateDto) {
        Task updatedTask = taskService.updateStatus(id, taskStatusUpdateDto.status());
        return taskMapper.toDto(updatedTask);
    }


    @PatchMapping("/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id, @RequestBody @Valid TaskUpdateDto taskUpdateDto) {
        Task updatedTask = taskService.patchTask(id, taskUpdateDto);
        return taskMapper.toDto(updatedTask);
    }
}
