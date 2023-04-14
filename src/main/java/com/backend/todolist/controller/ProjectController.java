package com.backend.todolist.controller;

import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.dto.searchdto.SearchInputDto;
import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.response.Response;
import com.backend.todolist.service.project.ProjectService;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping("/search")
    Response<Pagination<ProjectOutputDto>> getAllProjects(Pageable pageable,SearchInputDto search) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(projectService.getAllByUserId(userDetailEntity.getAccount().getUserId(), pageable, search));
    }
    @GetMapping
    Response<ProjectOutputDto> getProjectById(@RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        ProjectOutputDto ProjectOutputDto = projectService.getProjectById(id, userDetailEntity.getAccount().getUserId());
        return Response.success(ProjectOutputDto);
    }
    @PostMapping
    Response<ProjectOutputDto> createProject(@RequestBody ProjectInputDto projectInputDto) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        ProjectOutputDto ProjectOutputDto = projectService.createProject(projectInputDto, userDetailEntity.getAccount().getUserId());
        return Response.success(ProjectOutputDto);
    }
    @PutMapping
    Response<ProjectOutputDto> updateProject(@RequestBody ProjectInputDto projectInputDto, @RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        ProjectOutputDto ProjectOutputDto = projectService.updateProject(projectInputDto, id, userDetailEntity.getAccount().getUserId());
        return Response.success(ProjectOutputDto);
    }
    @DeleteMapping
    Response deleteProject(@RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        projectService.deleteProject(id, userDetailEntity.getAccount().getUserId());
        return Response.success();
    }

    private UserDetailEntity getUserDetailEntity() {
        return (UserDetailEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
