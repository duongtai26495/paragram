package net.accessory.paragram.services;

import net.accessory.paragram.entities.ProductImage;
import net.accessory.paragram.entities.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductImageService {
    ResponseEntity<ResponseObject> saveImageWithProductId(ProductImage productImage);
    void deleteImageById(Long id);
    String getNameImageById(Long id);
    List<String> getAllImageByProductId(Long id);
}
