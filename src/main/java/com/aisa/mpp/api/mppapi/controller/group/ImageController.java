package com.aisa.mpp.api.mppapi.controller.group;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.aisa.mpp.api.mppapi.model.group.ProductImage;
import com.aisa.mpp.api.mppapi.repository.group.ProductImageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ProductImageRepository productImageRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("imageName") String imageName,
            @RequestParam("imageFile") MultipartFile file) {
        try {
            ProductImage productImage = new ProductImage();
            productImage.setImageName(imageName);
            productImage.setImage(file.getBytes());

            productImageRepository.save(productImage);

            return ResponseEntity.ok("Image uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Image upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<ProductImage> productImageOptional = productImageRepository.findById(id);

        if (productImageOptional.isPresent()) {
            ProductImage productImage = productImageOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + productImage.getImageName() + "\"")
                    .contentType(getContentType(productImage.getImageName()))
                    .body(productImage.getImage());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private org.springframework.http.MediaType getContentType(String imageName) {
        String fileExtension = imageName.substring(imageName.lastIndexOf(".") + 1);
        switch (fileExtension.toLowerCase()) {
            case "png":
                return org.springframework.http.MediaType.IMAGE_PNG;
            case "jpeg":
            case "jpg":
                return org.springframework.http.MediaType.IMAGE_JPEG;
            case "gif":
                return org.springframework.http.MediaType.IMAGE_GIF;
            default:
                return org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    @PostMapping("/uploadMultiple")
    public ResponseEntity<List<ProductImage>> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files) {
        List<ProductImage> savedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                ProductImage productImage = new ProductImage();
                productImage.setImageName(file.getOriginalFilename());
                productImage.setImage(file.getBytes());
                savedImages.add(productImageRepository.save(productImage));
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        }

        return ResponseEntity.ok(savedImages);
    }
}
