package com.athletiquest.athletiquest_api.dto.repository;

import com.athletiquest.athletiquest_api.dto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUsername(String username);

    User findByEmail(String email);
}
