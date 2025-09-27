package edu.dosw.taller.model.persistence.repository;
import java.time.LocalDateTime;
import edu.dosw.taller.model.entities.Task;
import edu.dosw.taller.model.entities.TaskStatus;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Task entity operations.
 * Extends MongoRepository to provide basic CRUD operations and custom query methods
 * for the collaborative task management system.
 * 
 */
@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    /**
     * Finds all tasks by their current status.
     * Useful for filtering tasks by PENDING, IN_PROGRESS, or COMPLETED states.
     * 
     * @param status the task status to filter by
     * @return a list of tasks matching the specified status
     */
    List<Task> findByStatus(TaskStatus status);

    /**
     * Finds all tasks created by a specific user.
     * 
     * @param creator the username of the task creator
     * @return a list of tasks created by the specified user
     */
    List<Task> findByCreator(String creator);

    /**
     * Finds all tasks containing a specific title (case-insensitive).
     * 
     * @param title the title keyword to search for
     * @return a list of tasks containing the specified title
     */
    List<Task> findByTitleContainingIgnoreCase(String title);
    
    /**
     * Finds all tasks containing a specific description (case-insensitive).
     * 
     * @param description the description keyword to search for
     * @return a list of tasks containing the specified description
     */
    List<Task> findByDescriptionContainingIgnoreCase(String description);
    
    /**
     * Searches for tasks by keyword in both title and description fields.
     * Uses MongoDB regex query to perform case-insensitive search across
     * both title and description fields using OR logic.
     * 
     * @param keyword the keyword to search for in title or description
     * @return a list of tasks containing the keyword in either title or description
     */
    @Query("{'$or': [{'title': {$regex: ?0, $options: 'i'}}, {'description': {$regex: ?0, $options: 'i'}}]}")
    List<Task> findByKeyword(String keyword);

    /**
     * Finds all tasks created within a specific date range.
     * 
     * @param startDate the start date of the range (inclusive)
     * @param endDate   the end date of the range (inclusive)
     * @return a list of tasks created within the specified date range
     */
    List<Task> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Finds all tasks scheduled for a specific date.
     * 
     * @param date the specific date to search for
     * @return a list of tasks scheduled for the specified date
     */
    List<Task> findByDate(LocalDateTime date);
    
    /**
     * Finds all tasks by their status and creator.
     * Useful for filtering tasks by both status and the user who created them.
     * 
     * @param status  the task status to filter by
     * @param creator the username of the task creator
     * @return a list of tasks matching the specified status and creator
     */
    @Query("{'status': ?0, 'creator': ?1}")
    List<Task> findByStatusAndCreator(TaskStatus status, String creator);

    /**
     * Counts the number of tasks by their status.
     * 
     * @param status the task status to filter by
     * @return the number of tasks matching the specified status
     */
    long countByStatus(TaskStatus status);

}
