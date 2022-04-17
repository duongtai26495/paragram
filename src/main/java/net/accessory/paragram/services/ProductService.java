package net.accessory.paragram.services;

import net.accessory.paragram.entities.Product;
import net.accessory.paragram.entities.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAll(int page, int size);

    ResponseEntity<ResponseObject> saveNew(Product product);

    ResponseEntity<ResponseObject> editById(Product product);

    ResponseEntity<ResponseObject> deleteById(Long id);

    ResponseEntity<ResponseObject> getProductById(Long id);

    boolean isExistByName(String name);

    List<Product> getProductsByBrandName(String code_name);

    List<Product> getProductsByCategoryName(String code_name);
}
