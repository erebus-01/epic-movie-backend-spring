package com.programing.crew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CrewApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CrewApplication.class, args);
    }
}
