package com.backend.todolist.dto.notedto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NoteInputDto {
    @NotNull
    private Long projectId;
    @NotNull
    @NotEmpty
    private String title;
    private String subtitle;
    private String description;
}
