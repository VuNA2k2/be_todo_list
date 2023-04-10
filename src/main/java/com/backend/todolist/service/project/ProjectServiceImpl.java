package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.repository.ProjectRepository;
import com.backend.todolist.service.task.TaskService;
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

    private final TaskService taskService;

    public ProjectServiceImpl(ProjectMapper projectMapper, ProjectRepository projectRepository, TaskService taskService) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
        this.taskService = taskService;
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
    public ProjectDetailOutputDto getProjectDetailById(Long projectId, Long userId) {
        if(!projectRepository.existsByIdAndUserId(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        return getProjectDetailOutputDtoFromProjectEntity(projectRepository.getByIdAndUserId(projectId, userId));
    }

    @Override
    public ProjectOutputDto getProjectById(Long projectId, Long userId) {
        return getProjectOutputDtoFromProjectEntity(projectRepository.getByIdAndUserId(projectId, userId));
    }

    @Override
    public ProjectDetailOutputDto createProject(ProjectInputDto projectInputDto, Long userId) {
        ProjectEntity projectEntity = projectMapper.getProjectEntityFromProjectInputDto(projectInputDto);
        projectEntity.setUserId(userId);
        if(projectEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.PROJECT_DEADLINE_IS_BEFORE_NOW;
        }
        return getProjectDetailOutputDtoFromProjectEntity(projectRepository.save(projectEntity));
    }

    @Override
    public ProjectDetailOutputDto updateProject(ProjectInputDto projectInputDto, Long projectId, Long userId) {
        if(!projectRepository.existsByIdAndUserId(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        ProjectEntity projectEntity = projectMapper.getProjectEntityFromProjectInputDto(projectInputDto);
        if(projectEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.PROJECT_DEADLINE_IS_BEFORE_NOW;
        }
        projectEntity.setUserId(userId);
        projectEntity.setId(projectId);
        return getProjectDetailOutputDtoFromProjectEntity(projectRepository.save(projectEntity));
    }

    @Override
    public void deleteProject(Long projectId, Long userId) {
        if(!projectRepository.existsByIdAndUserId(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectDetailOutputDto getProjectDetailOutputDtoFromProjectEntity(ProjectEntity projectEntity) {
        ProjectDetailOutputDto projectDetailOutputDto = projectMapper.getProjectDetailOutputDtoFromProjectEntity(projectEntity);
        projectDetailOutputDto.setTasks(taskService.getAllTaskByProjectId(projectEntity.getId())).setNotes(List.of());
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
