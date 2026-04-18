package todo;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test; // För att direkt interagera med databasen i tester
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    //@Autowired
    //private TestEntityManager entityManager; // Används för att "flusha" och "clear" databasen om nödvändigt, eller för att sätta upp testdata

    @Test
    @DisplayName("Test save and find task")
    void shouldSaveAndFindTask() {
        Task task = new Task("Test Task", "En beskrivning", "TODO");
        Task savedTask = taskRepository.save(task);

        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isNotNull();

        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());
        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getTitle()).isEqualTo("Test Task");
    }

    @Test
    @DisplayName("Test find all tasks")
    void shouldFindAllTasks() {
        taskRepository.save(new Task("Task 1", "Desc 1", "TODO"));
        taskRepository.save(new Task("Task 2", "Desc 2", "DOING"));

        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(2);
    }

    @Test
    @DisplayName("Test update task")
    void shouldUpdateTask() {
        Task task = new Task("Original Title", "Original Desc", "TODO");
        Task savedTask = taskRepository.save(task);

        savedTask.setTitle("Updated Title");
        savedTask.setStatus("DONE");
        Task updatedTask = taskRepository.save(savedTask);

        assertThat(updatedTask.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedTask.getStatus()).isEqualTo("DONE");
    }

    @Test
    @DisplayName("Test delete task")
    void shouldDeleteTask() {
        Task task = new Task("Task to Delete", "Desc", "TODO");
        Task savedTask = taskRepository.save(task);

        taskRepository.deleteById(savedTask.getId());

        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());
        assertThat(foundTask).isNotPresent();
    }
}
