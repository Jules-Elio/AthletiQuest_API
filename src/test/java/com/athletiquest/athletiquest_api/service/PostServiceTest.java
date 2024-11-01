package com.athletiquest.athletiquest_api.service;

import com.athletiquest.athletiquest_api.dto.entity.Post;
import com.athletiquest.athletiquest_api.dto.repository.PostRepository;
import com.athletiquest.athletiquest_api.dto.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;


    @Test
    void getAllUsers_noUser() {
        List<Post> posts = List.of();
        when(postRepository.findAll()).thenReturn(posts);
        assertThat(postService.findAll()).isEmpty();
    }

    @Test
    void getAllUsers_oneUser() {
        List<Post> posts = List.of(new Post());
        when(postRepository.findAll()).thenReturn(posts);
        assertThat(postService.findAll()).hasSize(1);
    }

    @Test
    void getUserById_found() {
        Post post = new Post();
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        assertThat(postService.findById(1L)).isEqualTo(post);
    }

    @Test
    void getUserById_notFound() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());
        assertThat(postService.findById(1L)).isNull();
    }

    @Test
    void saveUser() {
        Post post = new Post();
        when(postRepository.save(post)).thenReturn(post);
        assertThat(postService.save(post)).isEqualTo(post);
        verify(postRepository).save(post);
    }

    @Test
    void deleteUser() {
        postService.delete(1L);
        verify(postRepository).deleteById(1L);
    }
}
