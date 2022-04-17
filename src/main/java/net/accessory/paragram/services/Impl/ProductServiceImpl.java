package net.accessory.paragram.services.Impl;

import net.accessory.paragram.entities.Product;
import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.repositories.ProductRepository;
import net.accessory.paragram.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageServiceImpl productImageService;

    private final String DATE_PATTERN = "dd/mm/yy hh:mm:ss";


    @Override
    public List<Product> getAll(int page, int size) {
        productRepository.findAll();
        Pageable paging = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findAll(paging);
        return productPage.toList();
    }

    @Override
    public ResponseEntity<ResponseObject> saveNew(Product product) {

            if (isExistByName(product.getName())){
                Product getProduct = productRepository.getProductByName(product.getName());
                getProduct.setQuantity(getProduct.getQuantity()+product.getQuantity());
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
                getProduct.setLast_edited_at(sdf.format(date));
                getProduct.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("SUCCESS","Edit product is successful",productRepository.save(getProduct))
                );
            }else{
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
                product.setCreated_at(sdf.format(date));
                product.setLast_edited_at(product.getCreated_at());
                product.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("SUCCESS","Create new product is successful",productRepository.save(product))
                );
            }


    }

    @Override
    public ResponseEntity<ResponseObject> editById(Product product) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
       if (productRepository.existsById(product.getId())){
           Product getProduct = productRepository.findById(product.getId()).get();
           getProduct.setId(product.getId());
           getProduct.setName(product.getName());
           getProduct.setBrand(product.getBrand());
           getProduct.setPrice(product.getPrice());
           getProduct.setQuantity(getProduct.getQuantity()+product.getQuantity());
           getProduct.setSale_rate(product.getSale_rate());
           getProduct.setImage_url(product.getImage_url());
           getProduct.setCategory(product.getCategory());
           getProduct.setActive(product.isActive());
           getProduct.setLast_edited_at(sdf.format(date));
           getProduct.setSale_price(product.getSale_price());
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("SUCCESS","Edit product is successful",productRepository.save(getProduct))
           );
       }else{
           return saveNew(product);
       }
    }

    @Override
    public ResponseEntity<ResponseObject> deleteById(Long id) {
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS","Delete product is successful",null)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("FAILED","Product not found",null)
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getProductById(Long id) {
        if(productRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS","Product with id "+id+ " founded",productRepository.getProductById(id))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED","Product with id "+id+" not found",null)
        );
    }


    @Override
    public boolean isExistByName(String name) {
        List<Product> products = productRepository.findAll();
        for (Product p : products){
            if(name.toLowerCase().trim().equals(p.getName().toLowerCase().trim())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> getProductsByBrandName(String code_name) {
        List<Product> products = productRepository.findAll();
        List<Product> getProducts = new ArrayList<>();
        for (Product product: products){
            if(product.getBrand().getCode_name().equals(code_name)){
                getProducts.add(product);
            }
        }
        return getProducts;
    }

    @Override
    public List<Product> getProductsByCategoryName(String code_name) {
        List<Product> products = productRepository.findAll();
        List<Product> getProducts = new ArrayList<>();
        for (Product product: products){
            if(product.getCategory().getCode_name().equals(code_name)){
                getProducts.add(product);
            }
        }
        return getProducts;
    }
}
