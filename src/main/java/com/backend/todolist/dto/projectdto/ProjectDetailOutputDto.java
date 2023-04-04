package com.backend.todolist.dto.projectdto;

import com.backend.todolist.dto.notedto.NoteOutputDto;
import com.backend.todolist.dto.taskdto.TaskOutputDto;
import com.backend.todolist.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProjectDetailOutputDto {
    private Long id;
    private String name;
    private String description;
    private String priority;
    private OffsetDateTime deadline;
    private Status status;
    private List<TaskOutputDto> tasks;
    private List<NoteOutputDto> notes;
}
