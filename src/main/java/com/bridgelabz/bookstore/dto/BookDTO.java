package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotNull
    String book_name;
    @NotNull
    String book_author;
    @NotNull
    String book_description;
    @NotNull
    String logo_multiPart;
    @NotNull
    double price;
    @NotNull
    int quantity;
}
