package net.accessory.paragram.services.Impl;

import net.accessory.paragram.entities.Brand;
import net.accessory.paragram.entities.ConvertCodeName;
import net.accessory.paragram.entities.GetCurrentUsername;
import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.repositories.BrandRepository;
import net.accessory.paragram.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;


    private final String DATE_PATTERN = "dd/MM/yy hh:mm:ss";

    @Override
    public ResponseEntity<ResponseObject> addNewBrand(Brand brand) {
        String currentUsername = GetCurrentUsername.getUsernameLogin();
        if (currentUsername!=null){
            brand.setCode_name(ConvertCodeName.convert(brand.getName()));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS","Create new brand is successful",brandRepository.save(brand))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("FAILURE","Create new brand is failure, user do not login",null)
        );

    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<ResponseObject> editById(Brand brand) {
        Brand getBrand = brandRepository.findById(brand.getId()).get();
        getBrand.setName(brand.getName());
        if (brand.getImage_url() != null){
            getBrand.setImage_url(brand.getImage_url());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("SUCCESS","Edit brand success",brandRepository.save(getBrand))
        );
    }
}