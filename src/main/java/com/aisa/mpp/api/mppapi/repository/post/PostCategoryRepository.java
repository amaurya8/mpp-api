package com.aisa.mpp.api.mppapi.repository.post;


import com.aisa.mpp.api.mppapi.model.post.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
}