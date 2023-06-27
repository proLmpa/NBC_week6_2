package com.sparta.bulletin.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long postId;
    private String commentContents;
}
