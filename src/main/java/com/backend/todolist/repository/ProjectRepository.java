package com.backend.todolist.repository;

import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    Optional<ProjectEntity> findById(Long id);
    @Query(value = "SELECT p FROM ProjectEntity p WHERE " +
            "p.userId = :userId " +
            "AND UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%'))" +
            "AND (:status IS NULL OR p.status = :status)")
    Page<ProjectEntity> searchInUser(Long userId, String name, Status status, Pageable pageable);
    void deleteById(Long id);
    ProjectEntity save(ProjectEntity projectEntity);

    void deleteByUserId(Long userId);

    @Override
    boolean existsById(Long id);
    boolean existsByIdAndUserId(Long id, Long userId);
}
