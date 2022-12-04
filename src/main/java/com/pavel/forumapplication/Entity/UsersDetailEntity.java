package com.pavel.forumapplication.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users-detail")
@DynamicUpdate
@DynamicInsert
public class UsersDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private int age;

    private String description;

    private LocalDateTime last_online;

    @Transient
    private boolean status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;
}
