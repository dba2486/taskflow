package com.taskflow.domain.task;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.common.BaseEntity;
import com.taskflow.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime dueDate;

    @Column(nullable = false)
    private Integer priority;

    // todo or done     (Enum 고려)
    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private boolean deleted = false;

    // Task - User (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Task - Category (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public void updateTask(String title, String description, Integer priority, String status, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
    }

    public void softDelete() {
        this.deleted = true;
    }
}
