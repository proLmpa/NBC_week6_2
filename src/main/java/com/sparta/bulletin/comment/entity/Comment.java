package com.sparta.bulletin.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.bulletin.comment.dto.CommentRequestDto;
import com.sparta.bulletin.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_contents", nullable = false)
    private String commentContents;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference // 원래는 @ManyToOne의 FETCHTYPE을 EAGER로 바꿔야 한다.
    private Post post;

    public Comment(CommentRequestDto requestDto, String username){
        this.commentContents = requestDto.getCommentContents();
        this.username = username;
    }

    public void update(CommentRequestDto requestDto) {
        this.commentContents = requestDto.getCommentContents();
    }
}
