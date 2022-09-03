package com.bridgelabz.bookstore.utility;

import org.springframework.stereotype.Component;

@Component
public class OTPGeneration {
    public int generateOTP(){
        return (int)Math.floor(Math.random()*(999999-100000+1)+100000);
    }
}
