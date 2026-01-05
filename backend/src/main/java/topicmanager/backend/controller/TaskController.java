package topicmanager.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import topicmanager.backend.dto.TaskCreateDto;
import topicmanager.backend.dto.TaskResponseDto;
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
    public TaskResponseDto createTask(@RequestBody TaskCreateDto taskCreateDto) {
        Task task = taskMapper.toEntity(taskCreateDto);
        Task savedTask = taskService.save(task);
        return taskMapper.toDto(savedTask);
    }


    @GetMapping()
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        return taskMapper.toDto(tasks);
    }
}
