package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.utils.exception.RestException;

import java.util.List;
public interface ProjectService {
    List<ProjectOutputDto> getAllByUserId(Long userId);
    ProjectDetailOutputDto getProjectDetailById(Long projectId, Long userId);
    ProjectOutputDto getProjectById(Long projectId, Long userId);
    ProjectDetailOutputDto createProject(ProjectInputDto projectInputDto, Long userId) throws RestException;
    ProjectDetailOutputDto updateProject(ProjectInputDto projectInputDto, Long projectId, Long userId);
    void deleteProject(Long projectId, Long userId);
    ProjectDetailOutputDto getProjectDetailOutputDtoFromProjectEntity(ProjectEntity projectEntity);

    ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity);
}
