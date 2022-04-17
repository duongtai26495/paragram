package net.accessory.paragram.controllers;

import net.accessory.paragram.entities.Category;
import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.services.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category/")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("new")
    public ResponseEntity<ResponseObject> createNew(@RequestBody Category category){
        return categoryService.saveNewCategory(category);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ResponseObject> editCategory(@PathVariable Long id,@RequestBody Category category){
        category.setId(id);
        return categoryService.editCategoryById(category);
    }


}
