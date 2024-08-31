package com.aisa.mpp.api.mppapi.service.post;
import com.aisa.mpp.api.mppapi.dto.post.ExtendiblePostResponse;
import com.aisa.mpp.api.mppapi.dto.post.PostDetail;
import com.aisa.mpp.api.mppapi.dto.post.PostResponse;
import com.aisa.mpp.api.mppapi.model.post.Post;
import com.aisa.mpp.api.mppapi.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public Map<String, ExtendiblePostResponse> getPostsGroupedByCategoryExtendible() {
        Map<String, ExtendiblePostResponse> response = new HashMap<>();

        //Fetching all posts
        List<Post> allPosts = postRepository.findAll();

        // Grouping posts by category
        for (Post post : allPosts) {
            String category = post.getCategory();
            String location = "IN"; // Set the location here, could be dynamic
            PostDetail postDetail = new PostDetail(post,post.getBase64Images());
            List <PostDetail> postDetailList = new ArrayList<>();
            postDetailList.add(postDetail);
            if (!response.containsKey(category)) {
                response.put(category, new ExtendiblePostResponse(category, location,postDetailList ));
            }
            else
                response.get(category).getPosts().add(postDetail);
        }

        return response;
    }
}