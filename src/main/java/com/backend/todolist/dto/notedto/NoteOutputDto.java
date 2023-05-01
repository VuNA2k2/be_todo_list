package com.backend.todolist.dto.notedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NoteOutputDto {
    private Long id;
    private Long projectId;
    private String title;
    private String subtitle;
    private String description;
}
