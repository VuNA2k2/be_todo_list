package com.backend.todolist.controller;

import com.backend.todolist.dto.projectdto.ProjectDetailOutputDto;
import com.backend.todolist.dto.projectdto.ProjectInputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.response.Response;
import com.backend.todolist.service.project.ProjectService;
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
        // TODO: hard code userId
        Long userId = 1L;
        List<ProjectOutputDto> projectOutputList = projectService.getAllByUserId(userId);
        return Response.success(new Pagination<>(projectOutputList.size(), projectOutputList));
    }
    @PostMapping
    Response<ProjectDetailOutputDto> createProject(@RequestBody ProjectInputDto projectInputDto) {
        Long userId = 1L;
        ProjectDetailOutputDto projectDetailOutputDto = projectService.createProject(projectInputDto);
        return Response.success(projectDetailOutputDto);
    }
    @PutMapping
    Response<ProjectDetailOutputDto> updateProject(@RequestBody ProjectInputDto projectInputDto, @RequestParam Long id) {
        ProjectDetailOutputDto projectDetailOutputDto = projectService.updateProject(projectInputDto, id);
        return Response.success(projectDetailOutputDto);
    }
    @DeleteMapping
    Response deleteProject(@RequestParam Long id) {
        projectService.deleteProject(id);
        return Response.success();
    }
    @GetMapping("/detail")
    Response<ProjectDetailOutputDto> getDetailProject(@RequestParam Long id) {
        ProjectDetailOutputDto projectDetailOutputDto = projectService.getProjectDetailById(id);
        return Response.success(projectDetailOutputDto);
    }
}
