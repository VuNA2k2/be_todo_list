package com.backend.todolist.entity;

import com.backend.todolist.utils.attributeconverter.PriorityConverter;
import com.backend.todolist.utils.attributeconverter.StatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "user_id")
    private Long userId;

    private OffsetDateTime deadline;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status;
}
