package com.backend.todolist.repository;

import com.backend.todolist.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    TaskEntity getById(Long taskId);

    void deleteById(Long taskId);

    TaskEntity save(TaskEntity taskEntity);

    boolean existsById(Long taskId);

    boolean existsByIdAndUserId(Long taskId, Long userId);

    List<TaskEntity> getAllByProjectId(Long projectId);

    Page<TaskEntity> getAllByProjectId(Pageable pageable, Long projectId);

    @Query("SELECT t FROM TaskEntity t JOIN ProjectEntity p WHERE p.userId = :userId")
    List<TaskEntity> getAllByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT t FROM TaskEntity t JOIN ProjectEntity p WHERE p.userId = :userId",
            countQuery = "SELECT count(t) FROM TaskEntity t JOIN ProjectEntity p WHERE p.userId = :userId")
    Page<TaskEntity> getAllByUserId(@Param("pageable") Pageable pageable, @Param("userId") Long userId);
}
