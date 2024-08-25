package com.aisa.mpp.api.mppapi.service.post;

import com.aisa.mpp.api.mppapi.model.post.PostCategory;
import com.aisa.mpp.api.mppapi.repository.post.PostCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCategoryService {

    @Autowired
    private PostCategoryRepository postCategoryRepository;

    public PostCategory getCategoryById(Long id) {
        return postCategoryRepository.findById(id).orElse(null);
    }
}
