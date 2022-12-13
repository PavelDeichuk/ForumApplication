package com.pavel.forumapplication.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.sql.Types;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@Audited
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @JdbcTypeCode(Types.BINARY)
    @NotAudited
    private byte[] bytes;

    private Long size;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_id")
    @JsonIgnore
    @NotAudited
    private UsersDetailEntity usersDetailEntity;

    @OneToOne
    @JoinColumn(name = "topic_id")
    @NotAudited
    @JsonIgnore
    private TopicEntity topicEntity;

    @OneToOne
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    @NotAudited
    private CommentEntity commentEntity;

    @OneToOne
    @JoinColumn(name = "news_id")
    @JsonIgnore
    @NotAudited
    private NewsEntity newsEntity;
}
