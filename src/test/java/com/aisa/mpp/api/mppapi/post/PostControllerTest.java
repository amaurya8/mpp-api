package com.aisa.mpp.api.mppapi.post;


import com.aisa.mpp.api.mppapi.controller.post.PostController;
import com.aisa.mpp.api.mppapi.model.post.Post;
import com.aisa.mpp.api.mppapi.repository.post.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@ActiveProfiles("test")
public class PostControllerTest {

    @Autowired
    private PostRepository postRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PostController(postRepository)).build();
    }

    @Test
    public void testCreatePost() throws Exception {

//        Post post = new Post();
//        post.setPostId(1L);
//        post.setTitle("Test Post");
//        post.setDescription("Description");
//        post.setCategory("Electronics");

        given()
                .contentType("multipart/form-data")
                .param("employeeId", 1L)
                .param("postType", "Buy")
                .param("title", "Test Post")
                .param("description", "Description")
                .param("category", "Electronics")
                .param("price", 100.00)
                .param("contactInfo", "contact@example.com")
                .param("status", "available")
                .multiPart("images", "test-image.jpg", "image-data".getBytes(), "image/jpeg")
                .when()
                .post("/api/posts/create")
                .then()
                .statusCode(200)
                .body("title", equalTo("Test Post"))
                .body("description", equalTo("Description"));
    }

    @Test
    public void testGetAllPosts() {

        given()
                .when()
                .get("/api/posts")
                .then()
                .statusCode(200)
                .body("[0].title", equalTo("Test Post"));
    }

    @Test
    public void testGetPostById() {

        given()
                .when()
                .get("/api/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Test Post"));
    }

    @Test
    public void testUpdatePost() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());         // Register the JavaTimeModule to handle Java 8 date/time types

        given()
                .contentType("multipart/form-data")
                .param("employeeId", 1L)
                .param("postType", "Buy")
                .param("title", "Updated Post")
                .param("description", "Description")
                .param("category", "Electronics")
                .param("price", 100.00)
                .param("contactInfo", "contact@example.com")
                .param("status", "available")
                .multiPart("images", "test-image.jpg", "image-data".getBytes(), "image/jpeg")
                .when()
                .put("/api/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Post"));
    }

    @Test
    public void testDeletePost() {

        given()
                .when()
                .delete("/api/posts/1")
                .then()
                .statusCode(204);
    }
}