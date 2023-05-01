package com.backend.todolist.dto.notedto;

import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NoteDetailOutputDto {
    private Long id;
    private ProjectOutputDto project;
    private String title;
    private String subtitle;
    private String description;
}
