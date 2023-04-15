package com.backend.todolist.dto.searchdto;
import com.backend.todolist.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SearchTaskInputDto extends SearchInputDto {
    private Long projectId;
    private Status statuses;
}
