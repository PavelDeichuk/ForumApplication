package com.pavel.forumapplication.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

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

    @OneToOne(mappedBy = "newsEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ImageEntity imageEntities;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;
}
