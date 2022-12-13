package com.pavel.forumapplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "comment")
@DynamicInsert
@DynamicUpdate
@Audited
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 12, max = 320, message = "description is min 12 max 320")
    @NotNull(message = "description is null")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @NotAudited
    private UsersEntity users;

    @OneToOne
    @JoinColumn(name = "topic_id")
    @JsonIgnore
    @NotAudited
    private TopicEntity topicEntity;

    @OneToOne(mappedBy = "commentEntity", cascade = CascadeType.ALL)
    @NotAudited
    @JsonIgnore
    private ImageEntity imageEntity;

}
