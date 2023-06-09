package com.backend.todolist.repository;

import com.backend.todolist.entity.Status;
import com.backend.todolist.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findById(Long taskId);

    void deleteById(Long taskId);

    void deleteByProjectId(Long projectId);

    Long countAllByProjectIdAndStatus(Long projectId, Status status);

    Long countAllByProjectId(Long projectId);

    TaskEntity save(TaskEntity taskEntity);

    boolean existsById(Long taskId);

    @Query("SELECT COUNT(*) > 0 FROM TaskEntity t JOIN ProjectEntity p ON " +
            "t.projectId = p.id JOIN UserEntity u ON " +
            "p.userId = u.userId WHERE t.id = :taskId AND u.userId = :userId")
    boolean existsByIdAndUserId(Long taskId, Long userId);

    boolean existsByIdAndProjectId(Long taskId, Long projectId);

    @Query(value = "SELECT t FROM TaskEntity t JOIN ProjectEntity p " +
            "ON p.id = :projectId AND t.projectId = p.id WHERE p.userId = :userId " +
            "AND UPPER(t.title) LIKE UPPER(CONCAT('%', :title, '%'))"+
            "AND (FUNCTION('DATE', t.deadline) = COALESCE(:deadline, FUNCTION('DATE', t.deadline)))" +
            "AND (:status IS NULL OR t.status = :status)")
    Page<TaskEntity> searchInProject(Long projectId, String title, Long userId, LocalDate deadline, Status status, Pageable pageable);

    @Query(value = "SELECT t FROM TaskEntity t JOIN ProjectEntity p " +
            "ON p.id = t.projectId AND t.projectId = p.id  WHERE " +
            "p.userId = :userId AND UPPER(t.title) LIKE UPPER(CONCAT('%', :title, '%')) " +
            "AND (FUNCTION('DATE', t.deadline) = COALESCE(:deadline, FUNCTION('DATE', t.deadline)))" +
            "AND (:status IS NULL OR t.status = :status)")
    Page<TaskEntity> searchInUser(Long userId, String title, LocalDate deadline, Status status, Pageable pageable);

}
