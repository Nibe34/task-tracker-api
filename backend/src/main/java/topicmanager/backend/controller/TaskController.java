package topicmanager.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import topicmanager.backend.dto.TaskCreateDto;
import topicmanager.backend.dto.TaskResponseDto;
import topicmanager.backend.mapper.TaskMapper;
import topicmanager.backend.model.Task;
import topicmanager.backend.service.TaskService;

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
}
