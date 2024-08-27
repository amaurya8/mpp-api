package com.aisa.mpp.api.mppapi.dto.post;

import com.aisa.mpp.api.mppapi.model.post.Post;

import java.util.List;

public class PostDetail {
    private Long postId;
    private Long employeeId;
    private String postType;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String contactInfo;
    private String status;
    private List<String> base64Images;
    private String createdAt;
    private String updatedAt;

    // Constructor
    public PostDetail(Post post, List<String> base64Images) {
        this.postId = post.getPostId();
        this.employeeId = post.getEmployeeId();
        this.postType = post.getPostType();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.category = post.getCategory();
        this.price = post.getPrice();
        this.contactInfo = post.getContactInfo();
        this.status = post.getStatus();
        this.base64Images = base64Images;
        this.createdAt = post.getCreatedAt().toString();
        this.updatedAt = post.getUpdatedAt().toString();
    }

    public PostDetail(Post post) {
    }

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getBase64Images() {
        return base64Images;
    }

    public void setBase64Images(List<String> base64Images) {
        this.base64Images = base64Images;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
