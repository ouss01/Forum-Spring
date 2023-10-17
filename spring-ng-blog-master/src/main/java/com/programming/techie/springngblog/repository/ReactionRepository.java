package com.programming.techie.springngblog.repository;


import com.programming.techie.springngblog.model.Post;
import com.programming.techie.springngblog.model.Reaction;
import com.programming.techie.springngblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findByUserId(Long userId);
    Optional<Reaction> findByPostId(Long postId);




}