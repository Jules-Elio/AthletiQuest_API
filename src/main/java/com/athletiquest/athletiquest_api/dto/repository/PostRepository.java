package com.athletiquest.athletiquest_api.dto.repository;

import com.athletiquest.athletiquest_api.dto.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
