package com.sparta.bulletin.comment.dto;

import com.sparta.bulletin.comment.entity.Comment;
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
