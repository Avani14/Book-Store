package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String first_name;
    private String last_name;
    private String KYC;
    private LocalDate DOB;
    @CreationTimestamp
    private LocalDate registration_date;
    @UpdateTimestamp
    private LocalDate updated_date;
    private String password;
    private String email;
    private boolean verify;
    private int otp;

    public User(UserDTO userDTO) {
        this.first_name = userDTO.getFirst_name();
        this.last_name = userDTO.getLast_name();
        this.KYC = userDTO.getKYC();
        this.DOB = userDTO.getDOB();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
    }
}
