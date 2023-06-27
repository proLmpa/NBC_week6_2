package com.sparta.bulletin.post.controller;

import com.sparta.bulletin.post.dto.PostResponseDto;
import com.sparta.bulletin.user.dto.UserResponseDto;
import com.sparta.bulletin.post.service.PostService;
import com.sparta.bulletin.post.dto.PostRequestDto;
import com.sparta.bulletin.user.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성하기 (요구사항.2)
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // UserDetails.getUser() : Authentication의 Principle
        return postService.createPost(requestDto, userDetails.getUser());
    }

    // 전체 게시글 작성 날짜 기준 내림차순으로 조회하기 (요구사항.1)
    @GetMapping("/post")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 선택한 게시글 조회하기 (요구사항.3)
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 선택한 게시글 수정하기 (요구사항.4)
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try{
            // UserDetails.getUser() : Authentication의 Principle
            return postService.updatePost(id, requestDto, userDetails.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // 선택한 게시글 삭제하기 (요구사항.5)
    @DeleteMapping("/post/{id}")
    public UserResponseDto deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            // UserDetails.getUser() : Authentication의 Principle
            postService.deletePost(id, userDetails.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new UserResponseDto(401L, "허가되지 않은 요청 API입니다.");
        }

        return new UserResponseDto(200L, id+"번째 게시물이 정상적으로 삭제되었습니다.");
    }
}
