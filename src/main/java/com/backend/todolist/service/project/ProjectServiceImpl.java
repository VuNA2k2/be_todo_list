package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.repository.ProjectRepository;
import com.backend.todolist.utils.exception.Errors;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectMapper projectMapper, ProjectRepository projectRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectOutputDto> getAllByUserId(Long userId) {
        return Optional
                .of(projectRepository.getAllByUserId(userId)
                                .stream()
                                .map(this::getProjectOutputDtoFromProjectEntity))
                .orElse(Stream.empty())
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDetailOutputDto getProjectDetailById(Long projectId) {
        if(!projectRepository.existsById(projectId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        return getProjectDetailOutputDtoFromProjectEntity(projectRepository.getById(projectId));
    }

    @Override
    public ProjectDetailOutputDto createProject(ProjectInputDto projectInputDto) {
        ProjectEntity projectEntity = projectMapper.getProjectEntityFromProjectInputDto(projectInputDto);
        if(projectEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.PROJECT_DEADLINE_IS_BEFORE_NOW;
        }
        return getProjectDetailOutputDtoFromProjectEntity(projectRepository.save(projectEntity));
    }

    @Override
    public ProjectDetailOutputDto updateProject(ProjectInputDto projectInputDto, Long projectId) {
        ProjectEntity projectEntity = projectMapper.getProjectEntityFromProjectInputDto(projectInputDto);
        projectEntity.setId(projectId);
        if(!projectRepository.existsById(projectEntity.getId())) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        if(projectEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.PROJECT_DEADLINE_IS_BEFORE_NOW;
        }
        return getProjectDetailOutputDtoFromProjectEntity(projectRepository.save(projectEntity));
    }

    @Override
    public void deleteProject(Long projectId) {
        if(!projectRepository.existsById(projectId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectDetailOutputDto getProjectDetailOutputDtoFromProjectEntity(ProjectEntity projectEntity) {
        ProjectDetailOutputDto projectDetailOutputDto = projectMapper.getProjectDetailOutputDtoFromProjectEntity(projectEntity);
        projectDetailOutputDto.setTasks(List.of()).setNotes(List.of());
        return projectDetailOutputDto;
    }

    @Override
    public ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity) {
        ProjectOutputDto projectOutputDto = projectMapper.getProjectOutputDtoFromProjectEntity(projectEntity);
        // TODO: get all task by project id and set progress
        projectOutputDto.setProgress(0.0);
        return projectOutputDto;

    }
}
