package com.aisa.mpp.api.mppapi.component.post;

import com.aisa.mpp.api.mppapi.model.post.PostCategory;
import com.aisa.mpp.api.mppapi.model.post.PostType;
import com.aisa.mpp.api.mppapi.repository.post.PostCategoryRepository;
import com.aisa.mpp.api.mppapi.repository.post.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PostCategoryRepository postCategoryRepository;

    @Autowired
    private PostTypeRepository postTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize PostCategory
        if (postCategoryRepository.count() == 0) {
            postCategoryRepository.saveAll(Arrays.asList(
                    new PostCategory("Mobile", LocalDateTime.now(), LocalDateTime.now()),
                    new PostCategory("Laptop", LocalDateTime.now(), LocalDateTime.now()),
                    new PostCategory("Flat", LocalDateTime.now(), LocalDateTime.now())
            ));
        }

        // Initialize PostType
        if (postTypeRepository.count() == 0) {
            postTypeRepository.saveAll(Arrays.asList(
                    new PostType("Buy", LocalDateTime.now(), LocalDateTime.now()),
                    new PostType("Sale", LocalDateTime.now(), LocalDateTime.now()),
                    new PostType("Rental", LocalDateTime.now(), LocalDateTime.now()),
                    new PostType("Others", LocalDateTime.now(), LocalDateTime.now())
            ));
        }
    }
}