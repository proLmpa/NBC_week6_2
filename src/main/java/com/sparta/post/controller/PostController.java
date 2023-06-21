package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
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
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest req) {
        return postService.createPost(requestDto, req);
    }

    // 전체 게시글 작성 날짜 기준 내림차순으로 조회하기 (요구사항.1)
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 선택한 게시글 조회하기 (요구사항.3)
    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 선택한 게시글 수정하기 (요구사항.4)
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 선택한 게시글 삭제하기 (요구사항.5)
    @DeleteMapping("/post/{id}")
    public PostResponseDto deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
