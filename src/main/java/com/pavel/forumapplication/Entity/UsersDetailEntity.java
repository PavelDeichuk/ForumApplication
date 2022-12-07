package com.pavel.forumapplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavel.forumapplication.Enum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users-detail")
@DynamicInsert
@DynamicUpdate
@Audited
public class UsersDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    private int age;

    private String description;

    private LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NotAudited
    private UsersEntity usersEntity;

    @OneToOne
    @JoinColumn(name = "image_id")
    @NotAudited
    @JsonIgnore
    private ImageEntity imageEntity;
}
