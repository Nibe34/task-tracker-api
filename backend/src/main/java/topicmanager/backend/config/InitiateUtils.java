package topicmanager.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import topicmanager.backend.dto.TaskCreateDto;
import topicmanager.backend.model.Status;
import topicmanager.backend.model.Task;
import topicmanager.backend.repository.TaskRepository;
import topicmanager.backend.service.TaskService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitiateUtils implements CommandLineRunner {
    private final TaskService taskService;
    private final TaskRepository taskRepository;


    @Override
    public void run(String... args) throws Exception {
        TaskCreateDto task1 = new TaskCreateDto("Task 1", "desc 1");
        TaskCreateDto task2 = new TaskCreateDto("Task 2", "desc 2");
        TaskCreateDto task3 = new TaskCreateDto("Task 3", "desc 3");
        TaskCreateDto task4 = new TaskCreateDto("Task 4", "desc 4");
        TaskCreateDto task5 = new TaskCreateDto("Task 5", "desc 5");
        TaskCreateDto task6 = new TaskCreateDto("Task 6", "desc 6");
        TaskCreateDto task7 = new TaskCreateDto("Task 7", "desc 7");
        TaskCreateDto task8 = new TaskCreateDto("Task 8", "desc 8");





        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);
        taskService.save(task5);
        taskService.save(task6);
        taskService.save(task7);
        taskService.save(task8);



        taskService.updateStatus((long)1, Status.IN_PROGRESS);
        taskService.updateStatus((long)3, Status.DONE);
        taskService.updateStatus((long)4, Status.IN_PROGRESS);
        taskService.updateStatus((long)6, Status.DONE);

        List<Task> tasks = taskService.findAll();

        for (Task task : tasks) {
            taskRepository.forceUpdateCreatedAt(task.getId(), Instant.now().minus(task.getId(), ChronoUnit.DAYS));
            System.out.println(task);
        }
    }
}
