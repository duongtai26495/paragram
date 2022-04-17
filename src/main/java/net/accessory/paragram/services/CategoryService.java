package net.accessory.paragram.services;

import net.accessory.paragram.entities.Category;
import net.accessory.paragram.entities.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<ResponseObject> saveNewCategory (Category category);
    ResponseEntity<ResponseObject> editCategoryById (Category category);
    ResponseEntity<ResponseObject> getAll();
}
