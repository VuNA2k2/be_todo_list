package com.backend.todolist.service.task;

import com.backend.todolist.dto.taskdto.TaskDetailOutputDto;
import com.backend.todolist.dto.taskdto.TaskInputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    List<TaskOutputDto> getAllTaskByProjectId(Long projectId, Long userId);
    List<TaskOutputDto> getAllTaskByProjectId(Long projectId);
    TaskDetailOutputDto getTaskDetail(Long taskId, Long userId);
    TaskDetailOutputDto createTask(TaskInputDto taskInputDto, Long userId);
    TaskDetailOutputDto updateTask(TaskInputDto taskInputDto, Long taskId, Long userId);
    void deleteTask(Long taskId);
    TaskDetailOutputDto getTaskDetailOutputDtoFromTaskEntity(TaskEntity taskEntity);
    boolean isTaskExist(Long taskId, Long userId);
    boolean isTaskExist(Long taskId);
}
