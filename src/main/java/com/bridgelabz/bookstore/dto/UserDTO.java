package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private String KYC;
    @NotNull
    private LocalDate DOB;
    @NotNull
    private String password;
    @NotNull
    private String email;

}
