package com.sparta.blog.comment.controller;

import com.sparta.blog.comment.dto.CommentRequestDto;
import com.sparta.blog.comment.dto.CommentResponseDto;
import com.sparta.blog.comment.service.CommentService;
import com.sparta.blog.user.dto.UserResponseDto;
import com.sparta.blog.user.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) { this.commentService = commentService; }

    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto, userDetails.getUser());
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable("id") Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try{
            return commentService.updateComment(id, requestDto, userDetails.getUser());
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/comment/{id}")
    public UserResponseDto deleteComment(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            commentService.deleteComment(id, userDetails.getUser());
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return new UserResponseDto(401L, "허가되지 않은 요청 API입니다.");
        }
        return new UserResponseDto(200L, id+"번 댓글이 정상적으로 삭제되었습니다.");
    }
}
