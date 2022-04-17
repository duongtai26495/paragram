package net.accessory.paragram.controllers;

import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.entities.Token;
import net.accessory.paragram.entities.User;
import net.accessory.paragram.services.Impl.TokenServiceImpl;
import net.accessory.paragram.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("edit/{username}")
    public ResponseEntity<ResponseObject> updateInfo(@PathVariable String username, @RequestBody User user){
        user.setUsername(username);
        return userService.editUserByUsername(user);
    }

    @PutMapping("updatepw")
    public ResponseEntity<ResponseObject> updatePassword(@RequestParam("token") String token, @RequestBody User user){

        tokenService.insertTokenToBlacklist(new Token(token));
        user.setUsername(user.getUsername());

        return  userService.updatePassword(user);
    }
}
