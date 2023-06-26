package com.sparta.post.service;

import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.dto.CommentResponseDto;
import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.jwt.JwtUtil;
import com.sparta.post.repository.CommentRepository;
import com.sparta.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, User user) {
        // 선택한 게시글의 DB 존재 여부 확인
        Post post = findPost(id);

        // 댓글 등록 후 등록된 댓글 반환하기
        Comment comment = new Comment(requestDto, user.getUsername());
        post.addCommentList(comment);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, Long commentId, CommentRequestDto requestDto, User user) throws Exception {
        // 선택한 게시글과 댓글이 DB이 존재하는지 확인
        Post post = findPost(id);
        Comment comment = findComment(commentId);

        // 해당 사용자가 작성한 댓글 여부 확인
        if(matchUser(comment, user)) {
            comment.update(requestDto);

            return new CommentResponseDto(comment);
        } else {
            throw new Exception("Unauthorized");
        }
    }

    public void deleteComment(Long id, Long commentId, User user) throws Exception{
        // 선택한 게시글과 댓글이 DB이 존재하는지 확인
        Post post = findPost(id);
        Comment comment = findComment(commentId);

        // 해당 사용자가 작성한 댓글 여부 확인
        if(matchUser(comment, user)) {
            commentRepository.delete(comment);

        } else {
            throw new Exception("Unauthorized");
        }
    }


    // 해당 게시글이 DB에 존재하는지 확인
    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다."));
    }

    public boolean matchUser(Comment comment, User user) {
        return comment.getUsername().equals(user.getUsername());
    }

}
