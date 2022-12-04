package com.pavel.forumapplication.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String password2;

    private String email;

    private String activation;

    private String passtoken;

    private String emailtoken;

    private String role;

    private boolean loginIsEmail;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersEntity")
    private UsersDetailEntity usersDetailEntity;
}
