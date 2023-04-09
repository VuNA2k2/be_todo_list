package com.backend.todolist.service.task;

import com.backend.todolist.dto.taskdto.TaskDetailOutputDto;
import com.backend.todolist.dto.taskdto.TaskInputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskEntity getTaskEntityFromTaskInputDto(TaskInputDto taskInputDto);
    TaskOutputDto getTaskOutputDtoFromTaskEntity(TaskEntity taskEntity);
    TaskDetailOutputDto getTaskDetailOutputDtoFromTaskEntity(TaskEntity taskEntity);
}
