package com.backend.todolist.service.task;

import com.backend.todolist.dto.taskdto.TaskDetailOutputDto;
import com.backend.todolist.dto.taskdto.TaskInputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.TaskEntity;
import com.backend.todolist.repository.TaskRepository;
import com.backend.todolist.service.project.ProjectService;
import com.backend.todolist.utils.exception.Errors;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    private final ProjectService projectService;

    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository,@Lazy ProjectService projectService) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.projectService = projectService;
    }

    @Override
    public List<TaskOutputDto> getAllTaskByProjectId(Long projectId, Long userId) {
        if (!projectService.isProjectExist(projectId, userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        return Optional.of(taskRepository.getAllByProjectId(projectId).stream().map(taskMapper::getTaskOutputDtoFromTaskEntity).collect(Collectors.toList()))
                .orElse(List.of());
    }

    @Override
    public List<TaskOutputDto> getAllTaskByProjectId(Long projectId) {
        if(!projectService.isProjectExist(projectId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
        return Optional.of(taskRepository.getAllByProjectId(projectId).stream().map(taskMapper::getTaskOutputDtoFromTaskEntity).collect(Collectors.toList()))
                .orElse(List.of());
    }

    @Override
    public TaskDetailOutputDto getTaskDetail(Long taskId, Long userId) {
        if(!isTaskExist(taskId, userId)) {
            throw Errors.TASK_NOT_FOUND;
        }
        return taskMapper.getTaskDetailOutputDtoFromTaskEntity(taskRepository.getById(taskId));
    }

    @Override
    public TaskDetailOutputDto createTask(TaskInputDto taskInputDto, Long userId) {
        TaskEntity taskEntity = taskMapper.getTaskEntityFromTaskInputDto(taskInputDto);
        if (taskEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.TASK_DEADLINE_IS_BEFORE_NOW;
        }
        return taskMapper.getTaskDetailOutputDtoFromTaskEntity(taskRepository.save(taskEntity));
    }

    @Override
    public TaskDetailOutputDto updateTask(TaskInputDto taskInputDto, Long taskId, Long userId) {
        if (!taskRepository.existsById(taskId)) {
            throw Errors.TASK_NOT_FOUND;
        }
        TaskEntity taskEntity = taskRepository.getById(taskId);
        if (taskEntity.getDeadline().isBefore(OffsetDateTime.now())) {
            throw Errors.TASK_DEADLINE_IS_BEFORE_NOW;
        }
        return taskMapper.getTaskDetailOutputDtoFromTaskEntity(taskRepository.save(taskEntity));
    }

    @Override
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw Errors.TASK_NOT_FOUND;
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDetailOutputDto getTaskDetailOutputDtoFromTaskEntity(TaskEntity taskEntity) {
        TaskDetailOutputDto taskDetailOutputDto = taskMapper.getTaskDetailOutputDtoFromTaskEntity(taskEntity);
        taskDetailOutputDto.setProject(projectService.getProjectById(taskEntity.getProjectId()));
        return taskDetailOutputDto;
    }

    @Override
    public boolean isTaskExist(Long taskId, Long userId) {
//        TODO: hard
        return true;
    }

    @Override
    public boolean isTaskExist(Long taskId) {
        return taskRepository.existsById(taskId);
    }
}
