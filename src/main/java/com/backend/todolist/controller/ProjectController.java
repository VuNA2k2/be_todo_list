package com.backend.todolist.controller;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.response.Response;
import com.backend.todolist.service.project.ProjectService;
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
    @GetMapping
    Response<Pagination<ProjectOutputDto>> getAllProjects() {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        List<ProjectOutputDto> projectOutputList = projectService.getAllByUserId(userDetailEntity.getAccount().getUserId());
        return Response.success(new Pagination<>(projectOutputList.size(), projectOutputList));
    }
    @PostMapping
    Response<ProjectDetailOutputDto> createProject(@RequestBody ProjectInputDto projectInputDto) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        ProjectDetailOutputDto projectDetailOutputDto = projectService.createProject(projectInputDto, userDetailEntity.getAccount().getUserId());
        return Response.success(projectDetailOutputDto);
    }
    @PutMapping
    Response<ProjectDetailOutputDto> updateProject(@RequestBody ProjectInputDto projectInputDto, @RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        ProjectDetailOutputDto projectDetailOutputDto = projectService.updateProject(projectInputDto, id, userDetailEntity.getAccount().getUserId());
        return Response.success(projectDetailOutputDto);
    }
    @DeleteMapping
    Response deleteProject(@RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        projectService.deleteProject(id, userDetailEntity.getAccount().getUserId());
        return Response.success();
    }
    @GetMapping("/detail")
    Response<ProjectDetailOutputDto> getDetailProject(@RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        ProjectDetailOutputDto projectDetailOutputDto = projectService.getProjectDetailById(id, userDetailEntity.getAccount().getUserId());
        return Response.success(projectDetailOutputDto);
    }

    private UserDetailEntity getUserDetailEntity() {
        return (UserDetailEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
