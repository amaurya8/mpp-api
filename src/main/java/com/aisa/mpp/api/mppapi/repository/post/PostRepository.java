package com.aisa.mpp.api.mppapi.repository.post;


import com.aisa.mpp.api.mppapi.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.contactInfo) LIKE LOWER(CONCAT('%', :query, '%'))")

    List<Post> findByQuery(@Param("query") String query);
}