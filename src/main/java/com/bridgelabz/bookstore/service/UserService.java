package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.dto.VerifyDTO;
import com.bridgelabz.bookstore.exceptions.UserNotFound;
import com.bridgelabz.bookstore.model.User;

import java.util.List;

public interface UserService {
    User getUserById(String id);
    List<User> getAllUsers();
    User addUser(UserDTO userDTO);
    User editUser(UserDTO userDTO,long id,String auth) throws UserNotFound;
    String deleteUser(long id,String auth) throws UserNotFound;
    String login(LoginDTO loginDTO) throws UserNotFound;
    boolean verifyUser(String email,int otp) throws UserNotFound;
}
