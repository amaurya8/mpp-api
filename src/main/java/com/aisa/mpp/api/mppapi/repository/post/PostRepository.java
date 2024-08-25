package com.aisa.mpp.api.mppapi.repository.post;


import com.aisa.mpp.api.mppapi.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}