package com.backend.todolist.entity;

import com.backend.todolist.utils.attributeconverter.NotificationTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;
    private String content;
    @Column(name = "time_stamp")
    private OffsetDateTime sendTime;
    @Convert(converter = NotificationTypeConverter.class)
    @Column(name = "notification_type")
    private NotificationType notificationType;
}
