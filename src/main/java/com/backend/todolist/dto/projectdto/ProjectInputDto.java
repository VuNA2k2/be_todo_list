package com.backend.todolist.dto.projectdto;

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
public class ProjectInputDto {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private Priority priority;
    private OffsetDateTime deadline;
    private Status status;
}
