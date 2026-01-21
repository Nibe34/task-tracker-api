package topicmanager.backend.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import topicmanager.backend.dto.*;
import topicmanager.backend.mapper.TaskMapper;
import topicmanager.backend.model.Task;
import topicmanager.backend.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    @PostMapping()
    public TaskResponseDto createTask(@RequestBody @Valid TaskCreateDto taskCreateDto) {
        Task task = taskMapper.toEntity(taskCreateDto);
        Task savedTask = taskService.save(task);
        return taskMapper.toDto(savedTask);
    }


    @GetMapping()
    public List<TaskResponseDto> getAllTasks(@ModelAttribute TaskFilterDto filter) {
        List<Task> tasks = taskService.searchTasks(filter);
        return taskMapper.toDto(tasks);
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
