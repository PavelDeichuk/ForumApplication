package com.pavel.forumapplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

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

    @Size(min = 5, max = 32, message = "Username is min 5 max 32")
    @NotNull(message = "username is null!")
    private String username;

    @Size(min = 8, max = 32, message = "Password is min 8 max 32")
    @NotNull(message = "Password is null!")
    private String password;

    private String password2;

    @Email(message = "Error validate email!")
    @Size(min = 5, max = 42, message = "email min 5 max 42")
    private String email;

    private String activation;

    private String passtoken;

    private String emailtoken;

    private String role;

    private boolean loginIsEmail;

    @OneToOne(mappedBy = "usersEntity")
    @JsonIgnore
    @NotAudited
    private UsersDetailEntity usersDetailEntity;

    @OneToMany(mappedBy = "usersEntity", fetch = FetchType.LAZY)
    @NotAudited
    @JsonIgnore
    private List<BanEntity> banEntity;

    @OneToMany(mappedBy = "author_ban", fetch = FetchType.LAZY)
    @NotAudited
    @JsonIgnore
    private List<BanEntity> banEntities;


}
