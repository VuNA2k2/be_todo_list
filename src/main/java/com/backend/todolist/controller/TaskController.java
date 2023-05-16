package com.backend.todolist.controller;

import com.backend.todolist.dto.searchdto.SearchTaskInputDto;
import com.backend.todolist.dto.taskdto.TaskDetailOutputDto;
import com.backend.todolist.dto.taskdto.TaskInputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.response.Response;
import com.backend.todolist.service.task.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    TaskService taskService;
    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/detail")
    Response<TaskDetailOutputDto> getTaskDetailById(@RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(taskService.getTaskDetail(id, userDetailEntity.getAccount().getUserId()));
    }
    @GetMapping("/search")
    Response<Pagination<TaskOutputDto>> searchTaskByUser(Pageable pageable, SearchTaskInputDto search) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(taskService.getTasksByUserId(userDetailEntity.getAccount().getUserId(), pageable, search));
    }
    @GetMapping("/search/{projectId}")
    Response<Pagination<TaskOutputDto>> searchTaskByProject(Pageable pageable, SearchTaskInputDto search, @PathVariable Long projectId) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(taskService.getTaskByProjectId(userDetailEntity.getAccount().getUserId(), pageable, search, projectId));
    }
    @PostMapping()
    Response<TaskDetailOutputDto> createTask(@RequestBody @Valid TaskInputDto taskInputDto) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(taskService.createTask(taskInputDto, userDetailEntity.getAccount().getUserId()));
    }
    @PutMapping()
    Response<TaskDetailOutputDto> updateTask(@RequestBody @Valid TaskInputDto taskInputDto, @RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(taskService.updateTask(taskInputDto, id, userDetailEntity.getAccount().getUserId()));
    }
    @DeleteMapping()
    Response<String> deleteTask(@RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        taskService.deleteTask(id, userDetailEntity.getAccount().getUserId());
        return Response.success();
    }
    private UserDetailEntity getUserDetailEntity() {
        return (UserDetailEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
