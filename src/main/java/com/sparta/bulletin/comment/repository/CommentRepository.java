package com.sparta.bulletin.comment.repository;

import com.sparta.bulletin.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
