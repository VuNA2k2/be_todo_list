package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.dto.searchdto.SearchProjectInputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.entity.Status;
import com.backend.todolist.repository.NoteRepository;
import com.backend.todolist.repository.ProjectRepository;
import com.backend.todolist.repository.TaskRepository;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.utils.exception.Errors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final NoteRepository noteRepository;

    public ProjectServiceImpl(ProjectMapper projectMapper, ProjectRepository projectRepository,/*, TaskService taskService*/TaskRepository taskRepository, NoteRepository noteRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public Pagination<ProjectOutputDto> getAllByUserId(Long userId, Pageable pageable, SearchProjectInputDto searchProject) {
        Pagination<ProjectOutputDto> pageOutputDto = new Pagination<>();
        Page<ProjectEntity> projectEntities = projectRepository.searchInUser(userId,
                searchProject != null ? searchProject.getKeyword() != null ? searchProject.getKeyword() : "" : "",
                searchProject != null ? searchProject.getStatus() : null, pageable);
        pageOutputDto.setItems(projectEntities.stream().map(this::getProjectOutputDtoFromProjectEntity).collect(Collectors.toList()));
        pageOutputDto.setTotals(projectEntities.getTotalElements());
        return pageOutputDto;
    }

    @Override
    public ProjectOutputDto getProjectById(Long projectId, Long userId) {
        if (!isProjectExist(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        ProjectEntity entity = projectRepository.findById(projectId).get();
        return getProjectOutputDtoFromProjectEntity(entity);
    }

    @Override
    public ProjectOutputDto createProject(ProjectInputDto projectInputDto, Long userId) {
        ProjectEntity projectEntity = projectMapper.getProjectEntityFromProjectInputDto(projectInputDto);
        projectEntity.setUserId(userId);
        if (projectEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.PROJECT_DEADLINE_IS_BEFORE_NOW;
        }
        return getProjectOutputDtoFromProjectEntity(projectRepository.save(projectEntity));
    }

    @Override
    public ProjectOutputDto updateProject(ProjectInputDto projectInputDto, Long projectId, Long userId) {
        if (!isProjectExist(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        ProjectEntity projectEntity = projectMapper.getProjectEntityFromProjectInputDto(projectInputDto);
        if (projectEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.PROJECT_DEADLINE_IS_BEFORE_NOW;
        }
        projectEntity.setUserId(userId);
        projectEntity.setId(projectId);
        return getProjectOutputDtoFromProjectEntity(projectRepository.save(projectEntity));
    }

    @Override
    public void deleteProject(Long projectId, Long userId) {
        if (!isProjectExist(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        taskRepository.deleteByProjectId(projectId);
        noteRepository.deleteByProjectId(projectId);
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity) {
        ProjectOutputDto projectOutputDto = projectMapper.getProjectOutputDtoFromProjectEntity(projectEntity);
        projectOutputDto.setCountAllTask(taskRepository.countAllByProjectId(projectEntity.getId()));
        projectOutputDto.setCountDoneTask(taskRepository.countAllByProjectIdAndStatus(projectEntity.getId(), Status.DONE));
        projectOutputDto.setProgress((double) (projectOutputDto.getCountAllTask() == 0 ? 0 : (projectOutputDto.getCountDoneTask() * 100) / projectOutputDto.getCountAllTask()));
        return projectOutputDto;
    }

    @Override
    public boolean isProjectExist(Long projectId, Long userId) {
        return projectRepository.existsByIdAndUserId(projectId, userId);
    }

    private void validate(ProjectEntity projectEntity) {
    }
}
