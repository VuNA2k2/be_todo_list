package com.backend.todolist.repository;

import com.backend.todolist.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    Optional<ProjectEntity> findById(Long id);
    Page<ProjectEntity> findAllByUserIdAndNameContainingIgnoreCase(Long userId, String name, Pageable pageable);
    void deleteById(Long id);
    ProjectEntity save(ProjectEntity projectEntity);

    @Override
    boolean existsById(Long id);
    boolean existsByIdAndUserId(Long id, Long userId);
}
