package com.bridgelabz.bookstore.utility;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }
}
