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

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rules")
@DynamicUpdate
@DynamicInsert
@Audited
public class RulesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "id rule is null!")
    private Long id_rule;

    @Size(min = 4, max = 52, message = "title is min 4 max 52")
    @NotNull(message = "title is null!")
    private String title;

    @Size(min = 12, max = 254, message = "description is min 12 max 254")
    @NotNull(message = "description is null!")
    private String description;

    private LocalDateTime timeout;

    @OneToMany(mappedBy = "rulesEntity", fetch = FetchType.LAZY)
    @NotAudited
    @JsonIgnore
    private List<BanEntity> banEntities;
}
