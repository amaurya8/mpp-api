package com.aisa.mpp.api.mppapi.controller.post;

import com.aisa.mpp.api.mppapi.model.post.Image;
import com.aisa.mpp.api.mppapi.model.post.Post;
import com.aisa.mpp.api.mppapi.repository.post.PostRepository;
import com.aisa.mpp.api.mppapi.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:63342")
public class PostController {

    @Autowired
    private PostService postService;

    public PostController(PostRepository postRepository) {
    }

    // Create a Post with Images
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("postType") String postType,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam("contactInfo") String contactInfo,
            @RequestParam("status") String status,
            @RequestParam("images") MultipartFile[] images) {

        try {
            List<Image> imageList = new ArrayList<>();
            for (MultipartFile file : images) {
                Image image = new Image(file.getBytes());
                imageList.add(image);
            }

            Post post = new Post();
            post.setEmployeeId(employeeId);
            post.setPostType(postType);
            post.setTitle(title);
            post.setDescription(description);
            post.setCategory(category);
            post.setPrice(price);
            post.setContactInfo(contactInfo);
            post.setStatus(status);
            post.setImages(imageList);

            Post savedPost = postService.savePost(post);
            return ResponseEntity.ok(savedPost);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // Get All Posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Get Post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("postType") String postType,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam("contactInfo") String contactInfo,
            @RequestParam("status") String status,
            @RequestParam(value = "images", required = false) MultipartFile[] images) {

        try {
            Optional<Post> existingPost = postService.getPostById(id);
            if (existingPost.isPresent()) {
                Post post = existingPost.get();
                post.setEmployeeId(employeeId);
                post.setPostType(postType);
                post.setTitle(title);
                post.setDescription(description);
                post.setCategory(category);
                post.setPrice(price);
                post.setContactInfo(contactInfo);
                post.setStatus(status);

                if (images != null) {
                    List<Image> imageList = new ArrayList<>();
                    for (MultipartFile file : images) {
                        Image image = new Image(file.getBytes());
                        imageList.add(image);
                    }
                    post.setImages(imageList);
                }

                Post updatedPost = postService.savePost(post);
                return ResponseEntity.ok(updatedPost);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // Delete a Post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postService.getPostById(id).isPresent()) {
            postService.deletePost(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}