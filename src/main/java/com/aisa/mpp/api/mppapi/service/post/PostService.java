package com.aisa.mpp.api.mppapi.service.post;
import com.aisa.mpp.api.mppapi.model.post.Post;
import com.aisa.mpp.api.mppapi.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Save a Post (Create or Update)
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // Get All Posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get Post by ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Delete a Post
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}