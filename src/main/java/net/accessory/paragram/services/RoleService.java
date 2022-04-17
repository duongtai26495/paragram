package net.accessory.paragram.services;

import net.accessory.paragram.entities.ResponseObject;
import net.accessory.paragram.entities.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    ResponseEntity<ResponseObject> saveNewRole(Role role);
    Role getRoleByName(String name);
    List<Role> getAll();
}
