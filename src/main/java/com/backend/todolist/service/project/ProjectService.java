package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.ProjectEntity;

import java.util.List;
public interface ProjectService {
    List<ProjectOutputDto> getAllByUserId(Long userId);
    ProjectDetailOutputDto getProjectDetailById(Long projectId);
    ProjectDetailOutputDto createProject(ProjectInputDto projectInputDto);
    ProjectDetailOutputDto updateProject(ProjectInputDto projectInputDto);
    void deleteProject(Long projectId);
    ProjectDetailOutputDto getProjectDetailOutputDtoFromProjectEntity(ProjectEntity projectEntity);

    ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity);
}
