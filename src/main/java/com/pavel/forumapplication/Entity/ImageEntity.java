package com.pavel.forumapplication.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @JdbcTypeCode(Types.BINARY)
    private byte[] bytes;

    private Long size;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_id")
    private UsersDetailEntity usersDetailEntity;

    @OneToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private CommentEntity commentEntity;

    @OneToOne
    @JoinColumn(name = "news_id")
    private NewsEntity newsEntity;
}
