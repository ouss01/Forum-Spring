package com.programming.techie.springngblog.controller;

import com.programming.techie.springngblog.dto.PostDto;
import com.programming.techie.springngblog.model.Post;
import com.programming.techie.springngblog.model.Reaction;

import com.programming.techie.springngblog.security.PostService;
import com.programming.techie.springngblog.service.BadWordDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private BadWordDetectionService badWordDetectionService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }



    @GetMapping("/all")
    public ResponseEntity<List<Post>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }

    @PostMapping("/{postId}/reactions/{userid}")
    public Reaction createReaction(@PathVariable Long postId, @RequestBody Reaction reaction, @PathVariable Long userid ) {
        return postService.createReaction(postId,reaction, userid );
    }

    @GetMapping
    public List<Post> getAllPostsWithReactions() {
        return postService.getAllPostsWithReactions();
    }

/*
    @GetMapping("/{postId}/bad-word-count/{badWord}")
    public ResponseEntity<Integer> getBadWordDetectionCount(@PathVariable Long postId, @PathVariable String text) {
        int count = badWordDetectionService.getBadWordDetectionCount(postId, text);
        return ResponseEntity.ok(count);
    }
*/

    // Update an existing post
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        boolean success = postService.updatePost(postId, postDto);
        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an existing post
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        boolean success = postService.deletePost(postId);
        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/reactions/{postId}/{userId}")
    public ResponseEntity<Void> deleteReaction(
            @PathVariable Long postId,
            @PathVariable Long userId) {

        if (postId != null) {
            boolean deleted = postService.deleteReaction(userId, postId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if (userId != null) {
            boolean deleted = postService.deleteReaction(postId, userId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            // handle missing postId and userId parameters
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/badword")
    public String create(@RequestBody PostDto postDto) {
        String content = postDto.getContent();
        String filteredContent = badWordDetectionService.detectAndReplaceBadWords(content);
        postDto.setContent(filteredContent);
        return content;
    }

}