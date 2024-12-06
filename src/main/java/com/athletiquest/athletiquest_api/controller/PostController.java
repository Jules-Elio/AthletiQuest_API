package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.Post;
import com.athletiquest.athletiquest_api.dto.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }


    @GetMapping()
    public List<Post> getPosts() {
        return service.findAll();
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return service.findById(postId);
    }

    @PostMapping("/add")
    public Post addPost(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        service.delete(postId);
    }
}
