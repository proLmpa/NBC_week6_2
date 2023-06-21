package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.jwt.JwtUtil;
import com.sparta.post.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository  postRepository;
    private final JwtUtil jwtUtil;

    public PostService(PostRepository postRepository, JwtUtil jwtUtil) {
        this.postRepository = postRepository;
        this.jwtUtil = jwtUtil;
    }

    // 게시글 작성하기 (요구사항.2)
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest req){
        User user = (User) req.getAttribute("user");
        // RequestDto -> Entity
        Post post = new Post(requestDto, user.getUsername());

        // DB 저장
        Post savedPost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(savedPost);

        return postResponseDto;
    }

    // 전체 게시글 작성 날짜 내림차 순으로 조회하기 (요구사항.1)
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    // 선택한 게시글 조회하기 (요구사항.3)
    public PostResponseDto getPost(Long id) {
        // 해당 게시글이 DB에 존재하는지 확인
        Post post = findPost(id);

        return new PostResponseDto(post);
    }

    // 선택한 게시글 수정하기 (요구사항.4)
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        // 해당 게시글이 DB에 존재하는지 확인
        Post post = findPost(id);

        // post 내용 수정
        post.update(requestDto);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    // 선택한 게시글 삭제하기 (요구사항.5)
    public PostResponseDto deletePost(Long id) {
        // 해당 게시글이 DB에 존재하는지 확인
        Post post = findPost(id);

        // post 내용 삭제
        postRepository.delete(post);

        return new PostResponseDto(200L, id+"번째 게시물이 정상적으로 삭제되었습니다.");
    }

    // 해당 게시글이 DB에 존재하는지 확인
    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }
}
