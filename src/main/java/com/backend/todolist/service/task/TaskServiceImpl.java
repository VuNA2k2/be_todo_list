package com.backend.todolist.service.task;

import com.backend.todolist.dto.searchdto.SearchTaskInputDto;
import com.backend.todolist.dto.taskdto.TaskDetailOutputDto;
import com.backend.todolist.dto.taskdto.TaskInputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.entity.TaskEntity;
import com.backend.todolist.repository.ProjectRepository;
import com.backend.todolist.repository.TaskRepository;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.utils.exception.Errors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public TaskDetailOutputDto getTaskDetail(Long taskId, Long userId) {
        if(!isTaskExist(taskId, userId)) throw Errors.TASK_NOT_FOUND;
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> Errors.TASK_NOT_FOUND);
        TaskDetailOutputDto taskDetailOutputDto = taskMapper.getTaskDetailOutputDtoFromTaskEntity(taskEntity);
        return taskDetailOutputDto;
    }

    @Override
    public Pagination<TaskOutputDto> getTaskByUserId(Long userId, Pageable pageable, SearchTaskInputDto searchTaskInputDto) {
        Pagination<TaskOutputDto> pagination = new Pagination<>();
        Page<TaskEntity> taskEntities = taskRepository.searchInUser(userId, searchTaskInputDto.getKeyword() != null ? searchTaskInputDto.getKeyword() : "", pageable);
        pagination.setItems(taskEntities.stream().map(taskMapper::getTaskOutputDtoFromTaskEntity).collect(Collectors.toList()));
        pagination.setTotals(taskEntities.getTotalElements());
        return pagination;
    }

    @Override
    public TaskDetailOutputDto createTask(TaskInputDto taskInputDto, Long userId) {
        if(!projectRepository.existsByIdAndUserId(taskInputDto.getProjectId(), userId)) throw Errors.PROJECT_NOT_FOUND;
        if(taskInputDto.getDeadline().isBefore(OffsetDateTime.now())) throw Errors.TASK_DEADLINE_IS_BEFORE_NOW;
        ProjectEntity projectEntity = projectRepository.findById(taskInputDto.getProjectId()).orElseThrow(() -> Errors.PROJECT_NOT_FOUND);
        if(taskInputDto.getDeadline().isAfter(projectEntity.getDeadline())) throw Errors.PROJECT_DEADLINE_IS_BEFORE_TASK_DEADLINE;
        return taskMapper.getTaskDetailOutputDtoFromTaskEntity(taskRepository.save(taskMapper.getTaskEntityFromTaskInputDto(taskInputDto)));
    }

    @Override
    public TaskDetailOutputDto updateTask(TaskInputDto taskInputDto, Long taskId, Long userId) {
        if(!isTaskExist(taskId, userId)) throw Errors.TASK_NOT_FOUND;
        if(!projectRepository.existsByIdAndUserId(taskInputDto.getProjectId(), userId)) throw Errors.PROJECT_NOT_FOUND;
        if(taskInputDto.getDeadline().isBefore(OffsetDateTime.now())) throw Errors.TASK_DEADLINE_IS_BEFORE_NOW;
        ProjectEntity projectEntity = projectRepository.findById(taskInputDto.getProjectId()).orElseThrow(() -> Errors.PROJECT_NOT_FOUND);
        if(taskInputDto.getDeadline().isAfter(projectEntity.getDeadline())) throw Errors.PROJECT_DEADLINE_IS_BEFORE_TASK_DEADLINE;
        return taskMapper.getTaskDetailOutputDtoFromTaskEntity(taskRepository.save(taskMapper.getTaskEntityFromTaskInputDto(taskInputDto)));
    }

    @Override
    public void deleteTask(Long taskId, Long userId) {

    }

    @Override
    public TaskDetailOutputDto getTaskDetailOutputDtoFromTaskEntity(TaskEntity taskEntity) {
        return null;
    }

    @Override
    public boolean isTaskExist(Long taskId, Long userId) {
        return taskRepository.existsByIdAndUserId(taskId, userId);
    }

    @Override
    public boolean isTaskExist(Long taskId) {
        return false;
    }

    @Override
    public Pagination<TaskOutputDto> getTaskByProjectId(Long userId, Pageable pageable, SearchTaskInputDto search, Long projectId) {
        Pagination<TaskOutputDto> pagination = new Pagination<>();
        Page<TaskEntity> taskEntities = taskRepository.searchInProject(projectId, search.getKeyword() != null ? search.getKeyword() : "", userId, pageable);
        pagination.setItems(taskEntities.stream().map(taskMapper::getTaskOutputDtoFromTaskEntity).collect(Collectors.toList()));
        pagination.setTotals(taskEntities.getTotalElements());
        return pagination;
    }
}
