package com.pavel.forumapplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
@Audited
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne(mappedBy = "newsEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    @NotAudited
    private ImageEntity imageEntities;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @NotAudited
    private UsersEntity usersEntity;
}
