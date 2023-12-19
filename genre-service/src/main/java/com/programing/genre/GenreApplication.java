package com.programing.genre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GenreApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GenreApplication.class, args);
    }
}
