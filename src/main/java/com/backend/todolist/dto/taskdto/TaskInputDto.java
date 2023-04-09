package com.backend.todolist.dto.taskdto;

import com.backend.todolist.entity.Priority;
import com.backend.todolist.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TaskInputDto {
    private Long projectId;
    private String title;
    private String subtitle;
    private String description;
    private Priority priority;
    private Long numberOfPomodoro;
    private OffsetDateTime deadline;
    private Boolean reminder;
    private Status status;
}
