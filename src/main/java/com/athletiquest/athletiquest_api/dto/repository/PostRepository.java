package com.athletiquest.athletiquest_api.dto.repository;

import com.athletiquest.athletiquest_api.dto.entity.Post;
import com.athletiquest.athletiquest_api.dto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthor(User author);
}
