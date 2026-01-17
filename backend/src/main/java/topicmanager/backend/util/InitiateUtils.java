package topicmanager.backend.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import topicmanager.backend.model.Status;
import topicmanager.backend.model.Task;
import topicmanager.backend.service.TaskService;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitiateUtils implements CommandLineRunner {
    private final TaskService taskService;


    @Override
    public void run(String... args) throws Exception {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("Task 1");
        task1.setStatus(Status.TODO);
        task1.setCreatedAt(Instant.now());


        Task task2 = new Task();
        task1.setTitle("Task 2");
        task1.setDescription("Task 2");
        task1.setStatus(Status.IN_PROGRESS);
        task1.setCreatedAt(Instant.now());



        taskService.save(task1);
        taskService.save(task2);


        List<Task> tasks = taskService.findAll();

        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
