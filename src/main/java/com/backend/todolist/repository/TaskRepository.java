package com.backend.todolist.repository;

import com.backend.todolist.entity.TaskEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    TaskEntity getById(Long taskId);
    void deleteById(Long taskId);

    TaskEntity save(TaskEntity taskEntity);
    boolean existsById(Long taskId);
    List<TaskEntity> getAllByProjectId(Long projectId);
    List<TaskEntity> getAllByProjectId(Pageable pageable, Long projectId);
}
