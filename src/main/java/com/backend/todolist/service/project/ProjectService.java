package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.utils.exception.RestException;

import java.util.List;
public interface ProjectService {
    List<ProjectOutputDto> getAllByUserId(Long userId);
    ProjectDetailOutputDto getProjectDetailById(Long projectId);
    ProjectOutputDto getProjectById(Long projectId);
    ProjectDetailOutputDto createProject(ProjectInputDto projectInputDto) throws RestException;
    ProjectDetailOutputDto updateProject(ProjectInputDto projectInputDto, Long projectId);
    void deleteProject(Long projectId);
    ProjectDetailOutputDto getProjectDetailOutputDtoFromProjectEntity(ProjectEntity projectEntity);

    ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity);
}
