package com.warsaw.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class WarsawHospitalBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(WarsawHospitalBackendApplication.class, args);
  }
}
