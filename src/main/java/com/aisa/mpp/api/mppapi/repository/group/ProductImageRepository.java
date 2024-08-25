package com.aisa.mpp.api.mppapi.repository.group;


import com.aisa.mpp.api.mppapi.model.group.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    // Additional query methods can be defined here if needed
}
