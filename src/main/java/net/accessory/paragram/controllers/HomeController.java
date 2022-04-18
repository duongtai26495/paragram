package net.accessory.paragram.controllers;

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
    private ImageServiceImpl imageService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("profile/{username}")
    public ResponseEntity<ResponseObject> getProfile(@PathVariable String username){
        return userService.getProfileByUsername(username);
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
