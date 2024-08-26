package com.aisa.mpp.api.mppapi.service.post;
import com.aisa.mpp.api.mppapi.dto.post.PostResponse;
import com.aisa.mpp.api.mppapi.model.post.Post;
import com.aisa.mpp.api.mppapi.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Map<String, List<PostResponse>> getAllPostsGroupedByCategory() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(post -> {
                    // Convert images to Base64 and return a response DTO
                    List<String> base64Images = post.getImages().stream()
                            .map(image -> Base64.getEncoder().encodeToString(image.getImageData()))
                            .collect(Collectors.toList());
                    return new PostResponse(post, base64Images);
                })
                .collect(Collectors.groupingBy(PostResponse::getCategory));
    }
}