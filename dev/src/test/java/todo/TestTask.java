package todo;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

class TaskTest {
    private Validator validator;

    @Test
    @DisplayName("Task ska vara giltig när alla fält är korrekta")
    void taskShouldBeValidWhenAllFieldsAreCorrect() {
        Task task = new Task("Giltig titel", "Beskrivning", "TODO");
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertThat(violations).isEmpty(); // Förväntar oss inga valideringsfel
    }

    @Test
    @DisplayName("Task ska inte vara giltig med tom titel")
    void taskShouldNotBeValidWithEmptyTitle() {
        Task task = new Task("", "Beskrivning", "TODO");
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertThat(violations).hasSize(1); // Förväntar oss ett valideringsfel
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Titel får inte vara tom");
    }

    @Test
    @DisplayName("Task ska inte vara giltig med null titel")
    void taskShouldNotBeValidWithNullTitle() {
        Task task = new Task(null, "Beskrivning", "TODO");
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertThat(violations).hasSize(1); // Förväntar oss ett valideringsfel
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Titel får inte vara tom");
    }

    @Test
    @DisplayName("Task ska inte vara giltig med för kort titel")
    void taskShouldNotBeValidWithTooShortTitle() {
        Task task = new Task("ab", "Beskrivning", "TODO");
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertThat(violations).hasSize(1); // Förväntar oss ett valideringsfel
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Titel måste vara mellan 3 och 100 tecken");
    }

    // Du kan lägga till fler tester för t.ex. för lång titel, eller att description/status inte valideras på samma sätt.
}
