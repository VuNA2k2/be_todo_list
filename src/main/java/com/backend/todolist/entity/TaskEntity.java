package com.backend.todolist.entity;

import com.backend.todolist.utils.attributeconverter.BitBooleanConverter;
import com.backend.todolist.utils.attributeconverter.PriorityConverter;
import com.backend.todolist.utils.attributeconverter.StatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalTime;
import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_id")
    private Long projectId;
    private String title;
    private String subtitle;
    private String description;
    @Convert(converter = PriorityConverter.class)
    private Priority priority;
    @Column(name = "number_of_pomodoro")
    private Long numberOfPomodoro;
    private OffsetDateTime deadline;
    @Column(name = "current_doing_time")
    private LocalTime currentDoingTime;
    @Convert(converter = BitBooleanConverter.class)
    private boolean reminder;
    @Convert(converter = StatusConverter.class)
    private Status status;
}
