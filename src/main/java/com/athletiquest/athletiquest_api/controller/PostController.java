package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Post;
import com.athletiquest.athletiquest_api.dto.service.PostService;
import com.athletiquest.athletiquest_api.dto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> result;
        try {
            result = service.findAll();
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        Post result;
        try {
            result = service.findById(postId);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        Post result;
        try {
            result = service.save(post);
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        try {
            service.delete(postId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getPostsOfUser(@PathVariable String userId) {
        List<Post> result;
        try {
            result = service.findAllByAuthor(userService.findById(userId));
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<List<Post>> getPostsCurrentUser() {
        List<Post> result;
        try {
            result = service.findAllByAuthor(userService.getCurrentUser());
        } catch (Exception _) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/currentUser/{postId}")
    public ResponseEntity<String> deletePostCurrentUser(@PathVariable Long postId) {
        try {
            if (service.isFromCurrentUser(postId)) {
                service.delete(postId);
            } else {
                return new ResponseEntity<>("You can't delete this post", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
