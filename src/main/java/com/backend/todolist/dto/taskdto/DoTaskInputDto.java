package com.backend.todolist.dto.taskdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoTaskInputDto {
    Time totalTime;
}
