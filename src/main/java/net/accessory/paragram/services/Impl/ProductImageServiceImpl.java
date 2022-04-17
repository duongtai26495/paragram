package net.accessory.paragram.services.Impl;

import net.accessory.paragram.entities.ProductImage;
import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.repositories.ProductImageRepository;
import net.accessory.paragram.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository imageRepository;


    @Override
    public ResponseEntity<ResponseObject> saveImageWithProductId(ProductImage productImage) {
        if (productImage!=null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS","Add new product image is successful",imageRepository.save(productImage))
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("FAILED","Add new product image is failure",null)
            );
        }
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public String getNameImageById(Long id) {
        ProductImage productImage = imageRepository.findById(id).get();
        return  productImage.getName();
    }

    @Override
    public List<String> getAllImageByProductId(Long id) {
        List<ProductImage> productImages = imageRepository.findAll();
        List<String> founded = new ArrayList<>();
        for (ProductImage productImage : productImages){
            if (productImage.getProduct().getId().equals(id)){
                founded.add(productImage.getName());
            }
        }
        return founded;
    }
}
