package com.example.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@IntegrationComponentScan
public class Service1Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Service1Application.class, args);
    }
}