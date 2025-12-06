package com.taskflow.domain.user;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.common.BaseEntity;
import com.taskflow.domain.task.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // User - Task (1:N)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    // User - Category (1:N)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    public void updateName(String name) {
        this.name = name;
    }
}
