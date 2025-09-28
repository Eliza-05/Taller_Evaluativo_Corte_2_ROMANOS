package edu.dosw.taller.model.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BasicTaskTest {

    @Test
    void shouldCreateBasicTaskWithValidParameters() {
        LocalDateTime now = LocalDateTime.now();
        BasicTask task = new BasicTask("Basic Task", "This is a basic task", now, "basicuser");

        assertNotNull(task.getId());
        assertEquals("Basic Task", task.getTitle());
        assertEquals("This is a basic task", task.getDescription());
        assertEquals(now, task.getDate());
        assertEquals("basicuser", task.getCreator());
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @Test
    void shouldInheritTaskBehavior() {
        LocalDateTime now = LocalDateTime.now();
        BasicTask task1 = new BasicTask("Task1", "Desc1", now, "user1");
        BasicTask task2 = new BasicTask("Task2", "Desc2", now, "user2");

        assertNotEquals(task1.getId(), task2.getId());
        assertTrue(task1 instanceof Task);
    }

    @Test
    void shouldAllowEmptyConstructor() {
        BasicTask task = new BasicTask();
        assertNotNull(task);
        assertTrue(task instanceof Task);
    }

    @Test
    void shouldAllowPropertyModification() {
        BasicTask task = new BasicTask("Initial", "Initial desc", LocalDateTime.now(), "user");

        task.setTitle("Updated Title");
        task.setStatus(TaskStatus.COMPLETED);

        assertEquals("Updated Title", task.getTitle());
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }
}
