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
