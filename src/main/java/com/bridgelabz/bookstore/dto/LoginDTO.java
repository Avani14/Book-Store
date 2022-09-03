package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class LoginDTO {
    String emailID;
    String password;

    public LoginDTO(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }
}
