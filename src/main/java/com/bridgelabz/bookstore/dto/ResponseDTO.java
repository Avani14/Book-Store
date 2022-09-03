package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    String message;
    Object obj;

    public ResponseDTO(String message, Object obj) {
        this.message = message;
        this.obj = obj;
    }
}
