package com.backend.todolist.repository;

import com.backend.todolist.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    ProjectEntity getByIdAndUserId(Long id, Long userId);
    List<ProjectEntity> getAllByUserId(Long userId);
    void deleteById(Long id);
    ProjectEntity save(ProjectEntity projectEntity);

    @Override
    boolean existsById(Long id);
    boolean existsByIdAndUserId(Long id, Long userId);
}
