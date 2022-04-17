package net.accessory.paragram.controllers;

import net.accessory.paragram.entities.Brand;
import net.accessory.paragram.entities.Product;
import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.entities.User;
import net.accessory.paragram.services.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "/api/")
@CrossOrigin
public class HomeController {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BrandServiceImpl brandService;

    @Autowired
    private ProductImageServiceImpl productImageService;

    @GetMapping("profile/{username}")
    public ResponseEntity<ResponseObject> getProfile(@PathVariable String username){
        return userService.getProfileByUsername(username);
    }

    @GetMapping("categories")
    public ResponseEntity<ResponseObject> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("brands")
    public List<Brand> getAllBrands(){
        return brandService.getAll();
    }

    @GetMapping("products")
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "12") Integer per){
        return productService.getAll(page,per);
    }

    @GetMapping("brand/{code_name}")
    public List<Product> getProductsByBrand(@PathVariable String code_name){
        return productService.getProductsByBrandName(code_name);
    }

    @GetMapping("category/{code_name}")
    public List<Product> getProductsByCategory(@PathVariable String code_name){
        return productService.getProductsByCategoryName(code_name);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @GetMapping("images_product/{id}")
    public List<String> allImageProduct(@PathVariable Long id){
        return productImageService.getAllImageByProductId(id);
    }

    @GetMapping("images/{fileName:.+}")
    public ResponseEntity<byte[]> readFile (@PathVariable String fileName){
        return imageService.readFile(fileName);
    }

    @PostMapping("register")
    public ResponseEntity<ResponseObject> register(@RequestBody User user){
        return userService.saveNewUser(user);
    }
}
