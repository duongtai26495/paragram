package net.accessory.paragram.controllers;

import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("search/{value}")
    public ResponseEntity<ResponseObject> searchUser (@PathVariable String value) {
        return userService.findListUserByEmailUsername(value);
    }
}
