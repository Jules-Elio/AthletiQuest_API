package com.athletiquest.athletiquest_api.auth;

import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by Email");
        }
        Set<GrantedAuthority> authorities = user.getRoles()
                                                .stream()
                                                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
                                                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }

}
