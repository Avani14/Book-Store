package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.dto.VerifyDTO;
import com.bridgelabz.bookstore.exceptions.UserNotFound;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getUserByID(@PathVariable String id){
        User user = userService.getUserById(id);
        ResponseDTO responseDTO = new ResponseDTO("User with id:"+id,user);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/get-all-users")
    public ResponseEntity<ResponseDTO> getUsers(){
        List<User> users = userService.getAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("All the registered users are",users);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody UserDTO userDTO){
        User users = userService.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Registered successfully!!!",users);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> editUser(@RequestBody UserDTO userDTO,@PathVariable long id,@RequestHeader(value = "Authorization") String auth) throws UserNotFound {
        User user = userService.editUser(userDTO,id,auth);
        ResponseDTO responseDTO = new ResponseDTO("User edited successfully!!!",user);
        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable long id,@RequestHeader(value = "Authorization") String auth) throws UserNotFound {
        String users = userService.deleteUser(id,auth);
        ResponseDTO responseDTO = new ResponseDTO("User deleted successfully!!!",users);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) throws UserNotFound {
        String token = userService.login(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Login successfully!!!",token);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/verify")
    public ResponseEntity<ResponseDTO> verify(@RequestBody VerifyDTO verifyDTO) throws UserNotFound {
        boolean ans = userService.verifyUser(verifyDTO.getEmail(),verifyDTO.getOtp());
        ResponseDTO responseDTO = new ResponseDTO("User verified successfully!!!",ans);
        return ResponseEntity.ok().body(responseDTO);
    }
}
