package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    User findUserByEmailAndOtp(String email,int otp);
    User findUserByEmailAndPassword(String email,String password);
}
