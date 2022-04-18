package net.accessory.paragram.services;

import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.entities.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseObject> saveNewUser(User user);

    ResponseEntity<ResponseObject> editUserByUsername(User user);

    ResponseEntity<ResponseObject>  updatePassword(User user);

    ResponseEntity<ResponseObject> getProfileByUsername(String username);

    ResponseEntity<ResponseObject> updateImageProfile(User user);
}
