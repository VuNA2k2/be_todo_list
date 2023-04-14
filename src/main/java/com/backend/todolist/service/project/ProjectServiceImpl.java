package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.dto.searchdto.SearchInputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.repository.ProjectRepository;
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


    public ProjectServiceImpl(ProjectMapper projectMapper, ProjectRepository projectRepository/*, TaskService taskService*/) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public Pagination<ProjectOutputDto> getAllByUserId(Long userId, Pageable pageable, SearchInputDto search) {
        Pagination<ProjectOutputDto> pageOutputDto = new Pagination<>();
        Page<ProjectEntity> projectEntities = projectRepository.findAllByUserIdAndNameContainingIgnoreCase(userId, search != null ? search.getKeyword() != null ? search.getKeyword() : "" : "", pageable);
        pageOutputDto.setItems(projectEntities.stream().map(projectMapper::getProjectOutputDtoFromProjectEntity).collect(Collectors.toList()));
        pageOutputDto.setTotals(projectEntities.getTotalElements());
        return pageOutputDto;
    }

    @Override
    public ProjectOutputDto getProjectById(Long projectId, Long userId) {
        if (!isProjectExist(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        return getProjectOutputDtoFromProjectEntity(projectRepository.findAllById(projectId));
    }

    @Override
    public ProjectOutputDto createProject(ProjectInputDto projectInputDto, Long userId) {
        ProjectEntity projectEntity = projectMapper.getProjectEntityFromProjectInputDto(projectInputDto);
        projectEntity.setUserId(userId);
        if (projectEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.PROJECT_DEADLINE_IS_BEFORE_NOW;
        }
        return projectMapper.getProjectOutputDtoFromProjectEntity(projectRepository.save(projectEntity));
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
        return projectMapper.getProjectOutputDtoFromProjectEntity(projectRepository.save(projectEntity));
    }

    @Override
    public void deleteProject(Long projectId, Long userId) {
        if (!isProjectExist(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity) {
        ProjectOutputDto projectOutputDto = projectMapper.getProjectOutputDtoFromProjectEntity(projectEntity);
        // TODO: get all task by project id and set progress
        projectOutputDto.setProgress(0.0);
        return projectOutputDto;
    }

    @Override
    public boolean isProjectExist(Long projectId, Long userId) {
        return projectRepository.existsByIdAndUserId(projectId, userId);
    }
}
