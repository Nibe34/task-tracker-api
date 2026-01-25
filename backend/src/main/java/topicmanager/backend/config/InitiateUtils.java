package topicmanager.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
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
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("desc 1");

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("desc 2");

        Task task3 = new Task();
        task3.setTitle("Task 3");
        task3.setDescription("desc 3");


        Task task4 = new Task();
        task4.setTitle("Task 4");
        task4.setDescription("desc 4");

        Task task5 = new Task();
        task5.setTitle("Task 5");
        task5.setDescription("desc 5");

        Task task6 = new Task();
        task6.setTitle("Task 6");
        task6.setDescription("desc 6");

        Task task7 = new Task();
        task7.setTitle("Task 7");
        task7.setDescription("desc 7");

        Task task8 = new Task();
        task8.setTitle("Task 8");
        task8.setDescription("desc 8");




        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);
        taskService.save(task5);
        taskService.save(task6);
        taskService.save(task7);
        taskService.save(task8);




        taskRepository.forceUpdateCreatedAt(task1.getId(), Instant.now().minus(1, ChronoUnit.DAYS));
        taskRepository.forceUpdateCreatedAt(task2.getId(), Instant.now().minus(5, ChronoUnit.DAYS));
        taskRepository.forceUpdateCreatedAt(task3.getId(), Instant.now().minus(2, ChronoUnit.DAYS));
        taskRepository.forceUpdateCreatedAt(task4.getId(), Instant.now().minus(6, ChronoUnit.DAYS));
        taskRepository.forceUpdateCreatedAt(task5.getId(), Instant.now().minus(3, ChronoUnit.DAYS));
        taskRepository.forceUpdateCreatedAt(task6.getId(), Instant.now().minus(7, ChronoUnit.DAYS));
        taskRepository.forceUpdateCreatedAt(task7.getId(), Instant.now().minus(4, ChronoUnit.DAYS));
        taskRepository.forceUpdateCreatedAt(task8.getId(), Instant.now().minus(8, ChronoUnit.DAYS));




        taskService.updateStatus((long)1, Status.IN_PROGRESS);
        taskService.updateStatus((long)3, Status.DONE);
        taskService.updateStatus((long)4, Status.IN_PROGRESS);
        taskService.updateStatus((long)6, Status.DONE);

        List<Task> tasks = taskService.findAll();

        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
