package com.backend.todolist.service.task;

import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.dto.searchdto.SearchTaskInputDto;
import com.backend.todolist.dto.taskdto.TaskDetailOutputDto;
import com.backend.todolist.dto.taskdto.TaskInputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.entity.Status;
import com.backend.todolist.entity.TaskEntity;
import com.backend.todolist.repository.ProjectRepository;
import com.backend.todolist.repository.TaskRepository;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.service.project.ProjectMapper;
import com.backend.todolist.utils.exception.Errors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;
    private final ProjectMapper projectMapper;
    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskMapper taskMapper, ProjectMapper projectMapper, TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public TaskDetailOutputDto getTaskDetail(Long taskId, Long userId) {
        if (!isTaskExist(taskId, userId)) throw Errors.TASK_NOT_FOUND;
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> Errors.TASK_NOT_FOUND);
        return getTaskDetailOutputDtoFromTaskEntity(taskEntity);
    }

    @Override
    public Pagination<TaskOutputDto> getTasksByUserId(Long userId, Pageable pageable, SearchTaskInputDto searchTaskInputDto) {
        Pagination<TaskOutputDto> pagination = new Pagination<>();
        Page<TaskEntity> taskEntities = taskRepository.searchInUser(userId,
                searchTaskInputDto.getKeyword() != null ?
                        searchTaskInputDto.getKeyword() :
                        "",
                searchTaskInputDto.getDeadline() != null ?
                        searchTaskInputDto.getDeadline().withOffsetSameInstant(ZoneOffset.UTC).toLocalDate() :
                        null,
                searchTaskInputDto.getStatus() != null ?
                        searchTaskInputDto.getStatus() :
                        null,
                pageable);
        pagination.setItems(taskEntities.stream().map(taskMapper::getTaskOutputDtoFromTaskEntity).collect(Collectors.toList()));
        pagination.setTotals(taskEntities.getTotalElements());
        return pagination;
    }

    @Override
    public TaskDetailOutputDto createTask(TaskInputDto taskInputDto, Long userId) {
        TaskEntity taskEntity = taskMapper.getTaskEntityFromTaskInputDto(taskInputDto);
        validate(taskEntity, userId);
        return getTaskDetailOutputDtoFromTaskEntity(taskRepository.save(taskEntity));
    }

    @Override
    public TaskDetailOutputDto updateTask(TaskInputDto taskInputDto, Long taskId, Long userId) {
        TaskEntity taskEntity = taskMapper.getTaskEntityFromTaskInputDto(taskInputDto);
        taskEntity.setId(taskId);
        validate(taskEntity, userId);
        return getTaskDetailOutputDtoFromTaskEntity(taskRepository.save(taskEntity));
    }

    @Override
    public void deleteTask(Long taskId, Long userId) {
        if (!isTaskExist(taskId, userId)) throw Errors.TASK_NOT_FOUND;
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDetailOutputDto getTaskDetailOutputDtoFromTaskEntity(TaskEntity taskEntity) {
        TaskDetailOutputDto taskDetailOutputDto = taskMapper.getTaskDetailOutputDtoFromTaskEntity(taskEntity);
        taskDetailOutputDto.setProject(getProjectById(taskEntity.getProjectId()));
        if(taskEntity.getCurrentDoingTime() == null) return taskDetailOutputDto;
        long totalMinutes = taskEntity.getNumberOfPomodoro() * 25;
        long currentMinutes = taskEntity.getCurrentDoingTime().getTime() / 60000;
        if(currentMinutes > totalMinutes) currentMinutes = totalMinutes;
        taskDetailOutputDto.setProgress((double) (currentMinutes * 100 / totalMinutes));
        return taskDetailOutputDto;
    }

    @Override
    public TaskOutputDto getTaskOutputDtoFromTaskEntity(TaskEntity taskEntity) {
        TaskOutputDto taskOutputDto = getTaskOutputDtoFromTaskEntity(taskEntity);
        long totalMinutes = taskEntity.getNumberOfPomodoro() * 25;
        if(taskEntity.getCurrentDoingTime() == null) return taskOutputDto;
        long currentMinutes = taskEntity.getCurrentDoingTime().getTime() / 60000;
        if(currentMinutes > totalMinutes) currentMinutes = totalMinutes;
        taskOutputDto.setProgress((double) (currentMinutes * 100 / totalMinutes));
        return taskOutputDto;
    }

    private void validate(TaskEntity taskEntity, Long userId) {
        if (taskEntity.getId() != null && !isTaskExist(taskEntity.getId(), userId)) throw Errors.TASK_NOT_FOUND;
        if (taskEntity.getDeadline().isBefore(OffsetDateTime.now())) throw Errors.TASK_DEADLINE_IS_BEFORE_NOW;
        if (!projectRepository.existsByIdAndUserId(taskEntity.getProjectId(), userId)) throw Errors.PROJECT_NOT_FOUND;
        ProjectEntity projectEntity = projectRepository.findById(taskEntity.getProjectId()).orElseThrow(() -> Errors.PROJECT_NOT_FOUND);
        if (taskEntity.getDeadline().isAfter(projectEntity.getDeadline()))
            throw Errors.PROJECT_DEADLINE_IS_BEFORE_TASK_DEADLINE;
        if (projectEntity.getStatus() == Status.DONE) {
            throw Errors.PROJECT_STATUS_IS_DONE;
        } else if (projectEntity.getStatus() == Status.TODO && taskEntity.getStatus() != Status.TODO && taskEntity.getStatus() != null) {
            throw Errors.PROJECT_STATUS_IS_TODO;
        }
    }

    @Override
    public boolean isTaskExist(Long taskId, Long userId) {
        return taskRepository.existsByIdAndUserId(taskId, userId);
    }

    @Override
    public boolean isTaskExist(Long taskId) {
        return taskRepository.existsById(taskId);
    }

    @Override
    public Pagination<TaskOutputDto> getTaskByProjectId(Long userId, Pageable pageable, SearchTaskInputDto search, Long projectId) {
        Pagination<TaskOutputDto> pagination = new Pagination<>();
        Page<TaskEntity> taskEntities = taskRepository.searchInProject(projectId,
                search.getKeyword() != null ?
                        search.getKeyword() :
                        "",
                userId,
                search.getDeadline() != null ?
                        search.getDeadline().withOffsetSameInstant(ZoneOffset.UTC).toLocalDate() :
                        null,
                search.getStatus(),
                pageable);
        pagination.setItems(taskEntities.stream().map(taskMapper::getTaskOutputDtoFromTaskEntity).collect(Collectors.toList()));
        pagination.setTotals(taskEntities.getTotalElements());
        return pagination;
    }

    private ProjectOutputDto getProjectById(Long id) {
        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(() -> Errors.PROJECT_NOT_FOUND);
        return projectMapper.getProjectOutputDtoFromProjectEntity(projectEntity);
    }
}
