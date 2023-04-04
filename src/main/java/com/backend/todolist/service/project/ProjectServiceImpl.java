package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.repository.ProjectRepository;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public ProjectDetailOutputDto createProject(ProjectInputDto projectInputDto) {
        return null;
    }

    @Override
    public ProjectDetailOutputDto updateProject(ProjectInputDto projectInputDto) {
        return null;
    }

    @Override
    public void deleteProject(Long projectId) {

    }

    @Override
    public ProjectDetailOutputDto getProjectDetailOutputDtoFromProjectEntity(ProjectEntity projectEntity) {
        return null;
    }

    @Override
    public ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity) {
        ProjectOutputDto projectOutputDto = projectMapper.getProjectOutputDtoFromProjectEntity(projectEntity);
        // TODO: get all task by project id and set progress
        projectOutputDto.setProgress(0.0);
        return projectOutputDto;

    }
}
