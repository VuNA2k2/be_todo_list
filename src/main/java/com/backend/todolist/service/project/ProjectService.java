package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.dto.searchdto.SearchInputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.utils.exception.RestException;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ProjectService {
    Pagination<ProjectOutputDto> getAllByUserId(Long userId, Pageable pageable, SearchInputDto search);
    ProjectOutputDto getProjectById(Long projectId, Long userId);
    ProjectOutputDto createProject(ProjectInputDto projectInputDto, Long userId);
    ProjectOutputDto updateProject(ProjectInputDto projectInputDto, Long projectId, Long userId);
    void deleteProject(Long projectId, Long userId);
    ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity);
    boolean isProjectExist(Long projectId, Long userId);
}
