package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.dto.entity.Post;
import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findAllByAuthor(User author) {
        return postRepository.findAllByAuthor(author);
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

    public boolean isFromCurrentUser(Long id) {
        Post post = findById(id);
        return post.getAuthor().equals(userService.getCurrentUser());
    }
}
