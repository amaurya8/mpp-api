package com.aisa.mpp.api.mppapi.dto.post;

import java.util.List;

public class ExtendiblePostResponse {
    private String category;
    private String location; // Add this new field
    private List<PostDetail> posts;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<PostDetail> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDetail> posts) {
        this.posts = posts;
    }

    public ExtendiblePostResponse(String category, String location, List<PostDetail> posts) {
        this.category = category;
        this.location = location; // Set newly added field location here
        this.posts = posts;
    }
}

