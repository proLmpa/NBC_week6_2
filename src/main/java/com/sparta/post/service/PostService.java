package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository  postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 작성하기 (요구사항.2)
    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

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

        // 비밀번호 일치 여부 확인
        if(matchPassword(post, requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        } else {
            // post 내용 수정
            post.update(requestDto);

            PostResponseDto postResponseDto = new PostResponseDto(post);

            return postResponseDto;
        }
    }

    // 선택한 게시글 삭제하기 (요구사항.5)
    public String deletePost(Long id, String password) {
        // 해당 게시글이 DB에 존재하는지 확인
        Post post = findPost(id);

        // 비밀번호 일치 여부 확인
        if(matchPassword(post, password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        } else {
            // post 내용 수정
            postRepository.delete(post);

            return id + "번째 게시글을 삭제했습니다!";
        }
    }

    // 해당 게시글이 DB에 존재하는지 확인
    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }

    // 비밀번호 일치 여부 확인하기
    public boolean matchPassword(Post post , String password){
        return !post.getPassword().equals(password);
    }
}
