package com.pavel.forumapplication.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@DynamicUpdate
@DynamicInsert
@Audited
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotAudited
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
    @NotAudited
    private UsersDetailEntity usersDetailEntity;
}
