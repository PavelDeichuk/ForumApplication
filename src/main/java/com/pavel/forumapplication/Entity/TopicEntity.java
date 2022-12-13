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

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topic")
@DynamicUpdate
@DynamicInsert
@Audited
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 8, max = 36, message = "name min 8 max 36")
    @NotNull(message = "name is null!")
    private String name;

    @Size(min = 12, max = 350, message = "description min 8 max 350")
    @NotNull(message = "description is null!")
    private String description;

    @ManyToMany(mappedBy = "topicEntities")
    @JsonIgnore
    @NotAudited
    private List<CategoryEntity> categoryEntities;

    @OneToOne(mappedBy = "topicEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    @NotAudited
    private ImageEntity imageEntity;
}
