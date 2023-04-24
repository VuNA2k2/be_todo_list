package com.backend.todolist.repository;

import com.backend.todolist.entity.NoteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    Optional<NoteEntity> findById(Long noteId);

    void deleteById(Long noteId);

    void deleteByProjectId(Long projectId);

    NoteEntity save(NoteEntity noteEntity);

    boolean existsById(Long noteId);

    boolean existsByIdAndProjectId(Long noteId, Long projectId);

    @Query("SELECT COUNT(*) > 0 FROM NoteEntity n JOIN ProjectEntity p ON n.projectId = p.id JOIN UserEntity u ON p.userId = u.userId WHERE n.id = :noteId AND u.userId = :userId")
    boolean existsByIdAndUserId(Long noteId, Long userId);

    @Query(value = "SELECT n FROM NoteEntity n JOIN ProjectEntity p " +
            "ON p.id = :projectId AND n.projectId = p.id WHERE p.userId = :userId " +
            "AND UPPER(n.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    Page<NoteEntity> searchInProject(Long projectId, String title, Long userId, Pageable pageable);

    @Query(value = "SELECT n FROM NoteEntity n JOIN ProjectEntity p " +
            "ON n.projectId = p.id WHERE p.userId = :userId " +
            "AND UPPER(n.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    Page<NoteEntity> searchInUser(Long userId, String title, Pageable pageable);
}
