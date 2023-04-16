package com.backend.todolist.service.task;

import com.backend.todolist.dto.searchdto.SearchTaskInputDto;
import com.backend.todolist.dto.taskdto.TaskDetailOutputDto;
import com.backend.todolist.dto.taskdto.TaskInputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.TaskEntity;
import com.backend.todolist.response.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskDetailOutputDto getTaskDetail(Long taskId, Long userId);
    Pagination<TaskOutputDto> getTaskByUserId(Long userId, Pageable pageable, SearchTaskInputDto searchTaskInputDto);
    TaskDetailOutputDto createTask(TaskInputDto taskInputDto, Long userId);
    TaskDetailOutputDto updateTask(TaskInputDto taskInputDto, Long taskId, Long userId);
    void deleteTask(Long taskId, Long userId);
    TaskDetailOutputDto getTaskDetailOutputDtoFromTaskEntity(TaskEntity taskEntity);
    TaskOutputDto getTaskOutputDtoFromTaskEntity(TaskEntity taskEntity);
    boolean isTaskExist(Long taskId, Long userId);

    boolean isTaskExist(Long taskId);

    Pagination<TaskOutputDto> getTaskByProjectId(Long userId, Pageable pageable, SearchTaskInputDto search, Long projectId);
}
