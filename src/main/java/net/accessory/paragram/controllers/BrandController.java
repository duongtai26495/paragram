package net.accessory.paragram.controllers;

import net.accessory.paragram.entities.Brand;
import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.services.Impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand/")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandService;


    @PostMapping("new")
    public ResponseEntity<ResponseObject> newBrand (@RequestBody Brand brand){
        return brandService.addNewBrand(brand);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById (@PathVariable Long id){
        brandService.deleteById(id);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ResponseObject> editById(@PathVariable Long id, @RequestBody Brand brand){
        brand.setId(id);
        return brandService.editById(brand);
    }
}
