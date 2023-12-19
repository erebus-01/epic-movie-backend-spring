package com.programing.series;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SeriesApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SeriesApplication.class, args);
    }
}
