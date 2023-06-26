package com.sparta.post.dto;

import com.sparta.post.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private String username;
    private String commentContents;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.username = comment.getUsername();
        this.commentContents = comment.getCommentContents();
    }
}
