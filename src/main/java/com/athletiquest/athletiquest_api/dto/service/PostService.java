package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.dto.entity.Post;
import com.athletiquest.athletiquest_api.dto.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
