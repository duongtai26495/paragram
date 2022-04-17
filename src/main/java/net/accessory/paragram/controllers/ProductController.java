package net.accessory.paragram.controllers;

import net.accessory.paragram.entities.Product;
import net.accessory.paragram.entities.ProductImage;
import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.services.Impl.CategoryServiceImpl;
import net.accessory.paragram.services.Impl.ImageServiceImpl;
import net.accessory.paragram.services.Impl.ProductImageServiceImpl;
import net.accessory.paragram.services.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/product/")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductImageServiceImpl productImageService;

    @PostMapping("uploadImage")
    public String uploadImage(@RequestParam("image")MultipartFile file){
      return imageService.storeFile(file);
    }

    @PostMapping("new")
    public ResponseEntity<ResponseObject> newProduct(@RequestBody Product product){
        return productService.saveNew(product);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ResponseObject> editProduct(@RequestBody Product product, @PathVariable Long id){
        product.setId(id);
        return productService.editById(product);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        return productService.deleteById(id);
    }

    @PostMapping("add_image")
    public ResponseEntity<ResponseObject> addImageProduct (@RequestBody ProductImage productImage){
        return productImageService.saveImageWithProductId(productImage);
    }
}
