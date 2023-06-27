package com.sparta.blog.comment.service;

import com.sparta.blog.comment.dto.CommentRequestDto;
import com.sparta.blog.comment.dto.CommentResponseDto;
import com.sparta.blog.comment.entity.Comment;
import com.sparta.blog.post.entity.Post;
import com.sparta.blog.user.entity.User;
import com.sparta.blog.common.jwt.JwtUtil;
import com.sparta.blog.comment.repository.CommentRepository;
import com.sparta.blog.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository, JwtUtil jwtUtil) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.jwtUtil = jwtUtil;
    }

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        // 선택한 게시글의 DB 존재 여부 확인
        Post post = findPost(requestDto.getPostId());

        // 댓글 등록 후 등록된 댓글 반환하기
        Comment comment = new Comment(requestDto, user.getUsername());
        post.addCommentList(comment);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, User user) throws Exception {
        // 선택한 댓글이 DB이 존재하는지 확인
        Comment comment = findComment(commentId);

        // 해당 사용자가 작성한 댓글 여부 확인
        if(matchUser(comment, user)) {
            comment.update(requestDto);

            return new CommentResponseDto(comment);
        } else {
            throw new Exception("Unauthorized");
        }
    }

    public void deleteComment(Long commentId, User user) throws Exception{
        // 선택한 댓글이 DB이 존재하는지 확인
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
