package edu.dosw.taller.model.services;

import edu.dosw.taller.model.FilterStrategy;
import edu.dosw.taller.model.components.TaskFactory;
import edu.dosw.taller.model.entities.Task;
import edu.dosw.taller.model.entities.TaskStatus;
import edu.dosw.taller.model.persistence.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock private TaskRepository taskRepository;
    @Mock private TaskFactory taskFactory;
    @Mock private FilterStrategy filterStrategy;

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService(taskRepository, taskFactory);
    }

    @Nested
    class CreateTask {

        @Test
        @DisplayName("debe crear y guardar una tarea usando el TaskFactory")
        void createTask_success() {
            LocalDateTime now = LocalDateTime.now();
            Task taskMock = mock(Task.class);

            when(taskFactory.createTask("titulo", "desc", now, "user"))
                    .thenReturn(taskMock);
            when(taskRepository.save(taskMock)).thenReturn(taskMock);

            Task result = service.createTask("titulo", "desc", now, "user");

            assertNotNull(result);
            assertEquals(taskMock, result);
            verify(taskFactory).createTask("titulo", "desc", now, "user");
            verify(taskRepository).save(taskMock);
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException si el título es nulo o vacío")
        void createTask_invalidTitle() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.createTask("  ", "desc", LocalDateTime.now(), "user"));
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException si el usuario es nulo o vacío")
        void createTask_invalidUser() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.createTask("titulo", "desc", LocalDateTime.now(), "   "));
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException si la fecha es nula")
        void createTask_nullDate() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.createTask("titulo", "desc", null, "user"));
        }
    }

    @Test
    @DisplayName("listTasks: debe retornar todas las tareas")
    void listTasks_returnsAll() {
        List<Task> data = List.of(mock(Task.class), mock(Task.class));
        when(taskRepository.findAll()).thenReturn(data);

        List<Task> result = service.listTasks();

        assertEquals(2, result.size());
        verify(taskRepository).findAll();
    }

    @Nested
    class FilterTasks {

        @Test
        @DisplayName("debe aplicar la estrategia de filtrado a la lista completa")
        void filterTasks_usesStrategy() {
            List<Task> all = List.of(mock(Task.class), mock(Task.class));
            List<Task> filtered = List.of(all.get(0));

            when(taskRepository.findAll()).thenReturn(all);
            when(filterStrategy.filter(all)).thenReturn(filtered);

            List<Task> result = service.filterTasks(filterStrategy);

            assertEquals(1, result.size());
            assertEquals(filtered, result);
            verify(taskRepository).findAll();
            verify(filterStrategy).filter(all);
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException si la estrategia es null")
        void filterTasks_nullStrategy() {
            assertThrows(IllegalArgumentException.class, () -> service.filterTasks(null));
        }
    }

    @Test
    @DisplayName("filterCompleted: debe consultar al repositorio por COMPLETED")
    void filterCompleted_returnsCompleted() {
        List<Task> completed = List.of(mock(Task.class));
        when(taskRepository.findByStatus(TaskStatus.COMPLETED)).thenReturn(completed);

        List<Task> result = service.filterCompleted();

        assertEquals(completed, result);
        verify(taskRepository).findByStatus(TaskStatus.COMPLETED);
    }

    @Nested
    class UpdateStatus {

        @Test
        @DisplayName("debe actualizar el estado y guardar la tarea")
        void updateStatus_success() {
            Task taskMock = mock(Task.class);
            when(taskRepository.findById("123")).thenReturn(Optional.of(taskMock));
            when(taskRepository.save(taskMock)).thenReturn(taskMock);

            Task result = service.updateStatus("123", TaskStatus.IN_PROGRESS);

            assertNotNull(result);
            verify(taskRepository).findById("123");
            verify(taskMock).setStatus(TaskStatus.IN_PROGRESS);
            verify(taskRepository).save(taskMock);
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException si el id es vacío")
        void updateStatus_emptyId() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.updateStatus("  ", TaskStatus.PENDING));
        }

        @Test
        @DisplayName("debe lanzar IllegalArgumentException si el estado es null")
        void updateStatus_nullStatus() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.updateStatus("123", null));
        }

        @Test
        @DisplayName("debe lanzar RuntimeException si no existe la tarea")
        void updateStatus_notFound() {
            when(taskRepository.findById("nope")).thenReturn(Optional.empty());
            assertThrows(RuntimeException.class,
                    () -> service.updateStatus("nope", TaskStatus.PENDING));
        }
    }

    @Nested
    class SearchTaskById {

        @Test
        @DisplayName("retorna Optional.empty cuando id es nulo o vacío")
        void searchTaskById_empty() {
            assertTrue(service.searchTaskById(null).isEmpty());
            assertTrue(service.searchTaskById("  ").isEmpty());
            verifyNoInteractions(taskRepository);
        }

        @Test
        @DisplayName("retorna Optional con la tarea cuando existe")
        void searchTaskById_found() {
            Task taskMock = mock(Task.class);
            when(taskRepository.findById("abc")).thenReturn(Optional.of(taskMock));

            Optional<Task> result = service.searchTaskById("abc");

            assertTrue(result.isPresent());
            assertEquals(taskMock, result.get());
            verify(taskRepository).findById("abc");
        }
    }

    @Nested
    class DeleteTask {

        @Test
        @DisplayName("retorna true si la tarea existe y se elimina")
        void deleteTask_found_true() {
            Task taskMock = mock(Task.class);
            when(taskRepository.findById("abc")).thenReturn(Optional.of(taskMock));

            boolean deleted = service.deleteTask("abc");

            assertTrue(deleted);
            verify(taskRepository).deleteById("abc");
        }

        @Test
        @DisplayName("retorna false si la tarea no existe")
        void deleteTask_notFound_false() {
            when(taskRepository.findById("abc")).thenReturn(Optional.empty());

            boolean deleted = service.deleteTask("abc");

            assertFalse(deleted);
            verify(taskRepository, never()).deleteById(anyString());
        }

        @Test
        @DisplayName("retorna false si el id es nulo o vacío")
        void deleteTask_invalidId_false() {
            assertFalse(service.deleteTask(null));
            assertFalse(service.deleteTask("   "));
            verifyNoInteractions(taskRepository);
        }
    }

    @Nested
    class Misc {

        @Test
        @DisplayName("setFilterStrategy: lanza IllegalArgumentException si es null")
        void setFilterStrategy_null_throws() {
            assertThrows(IllegalArgumentException.class, () -> service.setFilterStrategy(null));
        }

        @Test
        @DisplayName("getTasksByCreator: usa el repositorio con el creador normalizado")
        void getTasksByCreator_ok() {
            when(taskRepository.findByCreator("liz")).thenReturn(List.of());

            List<Task> result = service.getTasksByCreator("  liz  ");

            assertNotNull(result);
            verify(taskRepository).findByCreator("liz");
        }

        @Test
        @DisplayName("getTasksByCreator: valida creador vacío")
        void getTasksByCreator_invalid() {
            assertThrows(IllegalArgumentException.class, () -> service.getTasksByCreator("  "));
        }

        @Test
        @DisplayName("searchByKeyword: delega en el repositorio")
        void searchByKeyword_ok() {
            when(taskRepository.findByKeyword("mongo")).thenReturn(List.of());

            List<Task> result = service.searchByKeyword(" mongo ");

            assertNotNull(result);
            verify(taskRepository).findByKeyword("mongo");
        }

        @Test
        @DisplayName("searchByKeyword: valida keyword vacía")
        void searchByKeyword_invalid() {
            assertThrows(IllegalArgumentException.class, () -> service.searchByKeyword("   "));
        }

        @Test
        @DisplayName("getTaskCountByStatus: retorna el conteo del repositorio")
        void getTaskCountByStatus_ok() {
            when(taskRepository.countByStatus(TaskStatus.PENDING)).thenReturn(3L);

            long count = service.getTaskCountByStatus(TaskStatus.PENDING);

            assertEquals(3L, count);
            verify(taskRepository).countByStatus(TaskStatus.PENDING);
        }

        @Test
        @DisplayName("getTaskCountByStatus: valida status nulo")
        void getTaskCountByStatus_null() {
            assertThrows(IllegalArgumentException.class, () -> service.getTaskCountByStatus(null));
        }
    }
}
