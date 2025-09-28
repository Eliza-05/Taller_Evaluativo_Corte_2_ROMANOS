package edu.dosw.taller.model.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskStatusTest {

    @Test
    void shouldHaveAllExpectedValues() {
        TaskStatus[] expectedValues = {TaskStatus.PENDING, TaskStatus.IN_PROGRESS, TaskStatus.COMPLETED};
        assertArrayEquals(expectedValues, TaskStatus.values());
    }

    @Test
    void shouldHaveCorrectValueOf() {
        assertEquals(TaskStatus.PENDING, TaskStatus.valueOf("PENDING"));
        assertEquals(TaskStatus.IN_PROGRESS, TaskStatus.valueOf("IN_PROGRESS"));
        assertEquals(TaskStatus.COMPLETED, TaskStatus.valueOf("COMPLETED"));
    }
}
