package com.taskflow.domain.category;

import com.taskflow.domain.common.BaseEntity;
import com.taskflow.domain.task.Task;
import com.taskflow.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    // Category - User (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Category - Task (1:N)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}
