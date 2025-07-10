package com.athletiquest.athletiquest_api.dto.service;

import com.athletiquest.athletiquest_api.auth.JwtTokenProvider;
import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.repository.RoleRepository;
import com.athletiquest.athletiquest_api.dto.repository.UserRepository;
import com.athletiquest.athletiquest_api.enums.RoleType;
import com.athletiquest.athletiquest_api.utils.LoginRequest;
import com.athletiquest.athletiquest_api.utils.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    public String signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        if (signUpRequest.getRoles() != null) {
            signUpRequest.getRoles().forEach(role -> user.getRoles().add(roleRepository.findByName(role)));
        } else {
            user.getRoles().add(roleRepository.findByRoleType(RoleType.USER));
        }
        User newUser = userRepository.saveAndFlush(user);

        return login(new LoginRequest(newUser.getEmail(), signUpRequest.getPassword()));
    }

    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
