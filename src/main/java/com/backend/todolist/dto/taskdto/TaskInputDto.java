package com.backend.todolist.dto.taskdto;

import com.backend.todolist.entity.Priority;
import com.backend.todolist.entity.Status;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Digits(integer = 10, fraction = 0)
    private Long projectId;
    @NotNull
    @NotEmpty
    private String title;
    private String subtitle;
    private String description;
    private Priority priority;
    @NotNull
    @Digits(integer = 10, fraction = 0)
    private Long numberOfPomodoro;
    @NotNull
    private OffsetDateTime deadline;
    private Boolean reminder;
    private Status status;
}
