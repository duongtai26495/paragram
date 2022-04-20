package net.accessory.paragram.services.Impl;

import net.accessory.paragram.configs.MyUserDetail;
import net.accessory.paragram.entities.*;
import net.accessory.paragram.repositories.UserRepository;
import net.accessory.paragram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
     UserRepository userRepository;

    @Autowired
     RoleServiceImpl roleService;

    @Autowired
     PasswordEncoder passwordEncoder;

    private final String DATE_PATTERN = "dd/MM/yy hh:mm:ss";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new MyUserDetail(user);
    }
    public UserServiceImpl() {

    }

    @Override
    public ResponseEntity<ResponseObject> saveNewUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("FAILED","This user with username "+user.getUsername()+" already taken!",null)
            );
        }
        if(userRepository.findByEmail(user.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("FAILED","This user with email "+user.getEmail()+" already taken!",null)
            );
        }
        List<Role> roleList = roleService.getAll();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        user.setJoined_at(sdf.format(date));
        user.setLast_edited(sdf.format(date));
        user.setActive(1);
        user.setRole(roleList.get(0));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDTO userDTO = ConvertUser.convertToDTO(userRepository.save(user));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("SUCCESS","Your account create is successful",userDTO)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> editUserByUsername(User user) {
        if (GetCurrentUsername.getUsernameLogin() != null ||
        GetCurrentUsername.getUsernameLogin().equals(user.getUsername())){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            User getUser = userRepository.findByUsername(user.getUsername());
            getUser.setFull_name(user.getFull_name());
            getUser.setRole(user.getRole());
            getUser.setGender(user.getGender());
            getUser.setLast_edited(sdf.format(date));
            UserDTO userDTO = ConvertUser.convertToDTO(userRepository.save(getUser));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS","Edit info user successfully",userDTO)
            );
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseObject("FAILED","User do not login or not permission",null)
            );
        }

    }

    @Override
    public ResponseEntity<ResponseObject> updatePassword(User user) {
        User getUser = userRepository.findByUsername(user.getUsername());
        getUser.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDTO userDTO = ConvertUser.convertToDTO(userRepository.save(getUser));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("SUCCESS","Password changed",userDTO)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> getProfileByUsername(String username) {
        if (username != null) {
            User user = userRepository.findByUsername(username);
            UserDTO userDTO = ConvertUser.convertToDTO(user);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS", "User found", userDTO)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "User not found or not permission", null)
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> updateImageProfile(User user) {
        User getUser = userRepository.findByUsername(user.getUsername());
        getUser.setAvatar(user.getAvatar());
        UserDTO userDTO = ConvertUser.convertToDTO(getUser);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("SUCCESS","Image profile changed",userDTO)
        );
    }
}
