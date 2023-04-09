package com.backend.todolist.service.task;

import com.backend.todolist.dto.taskdto.TaskDetailOutputDto;
import com.backend.todolist.dto.taskdto.TaskInputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    List<TaskOutputDto> getAllTaskByProjectId(Long projectId);
    TaskDetailOutputDto getTaskDetail(Long taskId);
    TaskDetailOutputDto createTask(TaskInputDto taskInputDto);
    TaskDetailOutputDto updateTask(TaskInputDto taskInputDto, Long taskId);
    void deleteTask(Long taskId);
    TaskDetailOutputDto getTaskDetailOutputDtoFromTaskEntity(TaskEntity taskEntity);
}
