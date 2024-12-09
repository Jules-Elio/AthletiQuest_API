package com.athletiquest.athletiquest_api.service;

import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.repository.UserRepository;
import com.athletiquest.athletiquest_api.dto.service.UserService;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void getAllUsers_noUser() {
        List<User> users = List.of();
        when(userRepository.findAll()).thenReturn(users);
        assertThat(userService.findAll()).isEmpty();
    }

    @Test
    void getAllUsers_oneUser() {
        List<User> users = List.of(new User());
        when(userRepository.findAll()).thenReturn(users);
        assertThat(userService.findAll()).hasSize(1);
    }

    @Test
    void getUserById_found() {
        User user = new User();
        when(userRepository.findById("0")).thenReturn(Optional.of(user));
        assertThat(userService.findById("0")).isEqualTo(user);
    }

    @Test
    void getUserById_notFound() {
        when(userRepository.findById("0")).thenReturn(Optional.empty());
        assertThat(userService.findById("0")).isNull();
    }

    @Test
    void getUserByUsername_found() {
        User user = new User();
        when(userRepository.findByUsername("Username")).thenReturn(user);
        assertThat(userService.findByUsername("Username")).isEqualTo(user);
    }

    @Test
    void getUserByUsername_notFound() {
        when(userRepository.findByUsername("Username")).thenReturn(null);
        assertThat(userService.findByUsername("Username")).isNull();
    }

    @Test
    void getUserByEmail_found() {
        User user = new User();
        when(userRepository.findByEmail("name@mail.com")).thenReturn(user);
        assertThat(userService.findByEmail("name@mail.com")).isEqualTo(user);
    }

    @Test
    void getUserByEmail_notFound() {
        when(userRepository.findByEmail("name@mail.com")).thenReturn(null);
        assertThat(userService.findByEmail("name@mail.com")).isNull();
    }

    @Test
    void saveUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.save(user)).isEqualTo(user);
        verify(userRepository).save(user);
    }

    @Test
    void deleteUser() {
        userService.delete("0");
        verify(userRepository).deleteById("0");
    }
}
