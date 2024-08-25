package com.aisa.mpp.api.mppapi.service.post;

import com.aisa.mpp.api.mppapi.model.post.PostType;
import com.aisa.mpp.api.mppapi.repository.post.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostTypeService {

    @Autowired
    private PostTypeRepository postTypeRepository;

    public PostType getTypeById(Long id) {
        return postTypeRepository.findById(id).orElse(null);
    }
}