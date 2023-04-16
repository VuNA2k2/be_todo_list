package com.backend.todolist.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Time;
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
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "number_of_pomodoro")
    private Long numberOfPomodoro;
    private OffsetDateTime deadline;
    @Column(name = "current_doing_time")
    private Time currentDoingTime = Time.valueOf("00:00:00");
    private boolean reminder;
    @Enumerated(EnumType.STRING)
    private Status status;
}
