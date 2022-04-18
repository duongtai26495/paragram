package net.accessory.paragram.controllers;

import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.entities.Token;
import net.accessory.paragram.entities.User;
import net.accessory.paragram.services.Impl.TokenServiceImpl;
import net.accessory.paragram.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TokenServiceImpl tokenService;

    @PutMapping("edit")
    public ResponseEntity<ResponseObject> updateInfo(@RequestBody User user){
        return userService.editUserByUsername(user);
    }

    @PutMapping("change_avt")
    public ResponseEntity<ResponseObject> updateAvt(@RequestBody User user){
        return userService.updateImageProfile(user);
    }

    @PutMapping("updatepw")
    public ResponseEntity<ResponseObject> updatePassword(@RequestParam("token") String token, @RequestBody User user){
        tokenService.insertTokenToBlacklist(new Token(token));
        return  userService.updatePassword(user);
    }
}
