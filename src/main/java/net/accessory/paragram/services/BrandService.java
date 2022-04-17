package net.accessory.paragram.services;

import net.accessory.paragram.entities.Brand;
import net.accessory.paragram.entities.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BrandService {
    ResponseEntity<ResponseObject> addNewBrand(Brand brand);

    List<Brand> getAll();

    void deleteById(Long id);

    ResponseEntity<ResponseObject> editById(Brand brand);
}
