package com.backend.todolist.service.project;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectEntity getProjectEntityFromProjectInputDto(ProjectInputDto projectInputDto);
    ProjectOutputDto getProjectOutputDtoFromProjectEntity(ProjectEntity projectEntity);
    ProjectDetailOutputDto getProjectDetailOutputDtoFromProjectEntity(ProjectEntity projectEntity);
}
