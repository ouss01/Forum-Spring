package com.programming.techie.springngblog.repository;

import com.programming.techie.springngblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}