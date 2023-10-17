package com.programming.techie.springngblog.security;

import com.programming.techie.springngblog.dto.PostDto;
import com.programming.techie.springngblog.exception.PostNotFoundException;
import com.programming.techie.springngblog.model.CurrentUser;
import com.programming.techie.springngblog.model.Post;
import com.programming.techie.springngblog.model.Reaction;
import com.programming.techie.springngblog.repository.PostRepository;
import com.programming.techie.springngblog.repository.ReactionRepository;
import com.programming.techie.springngblog.repository.UserRepository;
import com.programming.techie.springngblog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Post> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    public void createPost(PostDto postDto) {
        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }
    @Transactional
    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        postDto.setNbrreaction(0);
        return postDto;
    }
    @Transactional
    public boolean updatePost(Long postId, PostDto postDto) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();

            // Update the fields of the post entity based on the data in the DTO
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());

            postRepository.save(post);
            return true;
        } else {
            return false;
        }
    }
    @Transactional
    public boolean deletePost(Long postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            postRepository.delete(postOpt.get());


            return true;
        } else {
            return false;
        }
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        CurrentUser loggedInUser =authService.getCurrentUser1();
        post.setCreatedOn(Date.from(Instant.now()));
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }

    public Reaction createReaction(Long postId, Reaction reaction, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
        com.programming.techie.springngblog.model.User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        reaction.setPost(post);
        post.setReaction(reaction);
        post.setNbrreaction(post.getNbrreaction() + 1);
        reaction.setUser(user); // Set the User association on the Reaction entity
        return reactionRepository.save(reaction);
    }
    public boolean deleteReaction(Long userId, Long postId) {
        if (postId != null) {
            Optional<Reaction> reactionOpt = reactionRepository.findByPostId(postId);
            if (reactionOpt.isPresent()) {
                reactionRepository.delete(reactionOpt.get());

                return true;
            } else {
                // add logging statement for debugging
                System.out.println("Reaction with postId " + postId + " not found.");
                return false;
            }
        } else if (userId != null) {
            Optional<Reaction> reactionOpt = reactionRepository.findByUserId(userId);
            if (reactionOpt.isPresent()) {
                reactionRepository.delete(reactionOpt.get());
                return true;
            } else {
                // add logging statement for debugging
                System.out.println("Reaction with userId " + userId + " not found.");
                return false;
            }
        } else {
            // handle missing postId and userId parameters
            throw new IllegalArgumentException("Either postId or userId must be provided.");
        }
    }

    public List<Post> getAllPostsWithReactions() {
        return postRepository.findAll();
    }
}