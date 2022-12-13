package com.pavel.forumapplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavel.forumapplication.Enum.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 16, message = "name min 3, max 16")
    private String name;
    @Size(min = 3, max = 18, message = "surname min 3, max 18")
    private String surname;

    private int age;

    @Size(min = 3, max = 302, message = "description min 3, max 302")
    private String description;

    private LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NotAudited
    @JsonIgnore
    private UsersEntity usersEntity;

    @OneToOne
    @JoinColumn(name = "image_id")
    @NotAudited
    @JsonIgnore
    private ImageEntity imageEntity;
}
