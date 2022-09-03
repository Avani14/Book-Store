package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResetDTO {
    @NotNull
    String email;
    @NotNull
    String old_pass;
    @NotNull
    String new_pass;
}
