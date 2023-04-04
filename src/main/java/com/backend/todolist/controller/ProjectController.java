package com.backend.todolist.controller;

import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.response.Response;
import com.backend.todolist.service.project.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
