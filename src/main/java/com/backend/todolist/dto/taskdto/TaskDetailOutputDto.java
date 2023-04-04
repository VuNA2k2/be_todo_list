package com.backend.todolist.dto.taskdto;

import com.backend.todolist.entity.Priority;

import java.time.LocalDateTime;

public class TaskDetailOutputDto {
    private Long id;
    private Long projectId;
    private String title;
    private String subtitle;
    private String description;
    private Priority priority;
    private Long numberOfPomodoro;
    private LocalDateTime deadline;
    private LocalDateTime currentDoingTime;
    private boolean reminder;
}
